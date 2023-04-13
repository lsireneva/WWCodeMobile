//
//  ContentView.swift
//  Hello SwiftUI
//
//  Created by Monika Gorkani on 1/25/23.
//

import SwiftUI

struct ContentView: View {
    @State var name = ""
    var body: some View {
        
            VStack {
                Image(systemName: "globe")
                    .imageScale(.large)
                    .foregroundColor(.accentColor)
                TextField("Enter your name", text:$name)
                    .padding(/*@START_MENU_TOKEN@*/.all/*@END_MENU_TOKEN@*/)
                    .border(Color.red, width: /*@START_MENU_TOKEN@*/1/*@END_MENU_TOKEN@*/)
                    .padding(.all)
                    
                Text(!name.isEmpty ? "Hello, \(name)!" : "Hello")
                    .font(.title)
                
                Button("Reset name") {
                    name = ""
                }
                .padding(.all)
                
            }
            
       
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
