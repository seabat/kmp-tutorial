import SwiftUI
import Shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    @State private var showContent = false
    
    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    List(viewModel.phrases, id: \.self) {
                        Text($0)
                    }.frame(height: 200)
                    Text(viewModel.rocketLaunchPhrase).task {
                        await self.viewModel.startRocketLaunchInfoObserving()
                    }
                    Text(viewModel.grepResult.description).task {
                        await self.viewModel.startGrep()
                    }
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

extension ContentView {
    @MainActor
    class ViewModel: ObservableObject {
        let sharedViewModel: GreetingSharedViewModel
        let loadRocketLaunchInfoUseCase: LoadRocketLaunchInfoUseCaseContract
        @Published var rocketLaunchPhrase: String = ""
        @Published var phrases:[String] = []

        @Published var grepResult: [String] = []
        private var didGrep = false
        
        init() {
            sharedViewModel = KoinHelperKt.getGreetingSharedViewModel()
            loadRocketLaunchInfoUseCase = KoinHelperKt.getLoadRocketLaunchInfoUseCase()
            sharedViewModel.observePhrases { phrases in
                self.phrases = phrases
            }
        }
        
        func startRocketLaunchInfoObserving() async {
            for await phrase in LoadRocketLaunchInfoUseCase(rocketRepository: RocketRepository()).invoke() {
                self.rocketLaunchPhrase = phrase
            }
        }
        
        func startGrep() async {
            guard !self.didGrep else { return } // 初回表示時のみ実行
            self.didGrep = true
            
            grep(
                lines: ["123 abc", "abc 123", "123 ABC", "ABC 123"],
                pattern: String("[A-Z]+"),
                action: {(result: String) -> Void in
                    self.grepResult.append(result)
                }
            )
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: ContentView.ViewModel())
    }
}
