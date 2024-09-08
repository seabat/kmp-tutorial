import SwiftUI
import Shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    @State private var showContent = false
    let phrases = GreetingShared().greet()
    
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
                    List(phrases, id: \.self) {
                        Text($0)
                    }.frame(height: 200)
                    Text(viewModel.rocketLaunchPhrase).task {
                        await self.viewModel.startObserving()
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
        @Published var rocketLaunchPhrase: String = ""

        func startObserving() async {
            for await phrase in RocketLaunchShared().getLaunchPhraseFlow() {
                self.rocketLaunchPhrase = phrase
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: ContentView.ViewModel())
    }
}
