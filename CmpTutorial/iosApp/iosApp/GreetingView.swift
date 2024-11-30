import SwiftUI
import ComposeApp

struct GreetingView: View {
    var body: some View {
        let greetings = Greeting().greet()
        if let firstGreeting = greetings.first {
            Text(firstGreeting)
        }
    }
}

#Preview {
    GreetingView()
}
