//
//  PopUpView.swift
//  TaskTracker
//
//  Created by Alexandra Harley on 2/22/24.
//

import SwiftUI

struct PopUpView: View {
    
    let text: String
    let firstButtonText: String
    let secondButtonText: String?
    let  okAction: (() -> Void)?
    @Binding var showButton: Bool
    @Binding var shouldDismiss: Bool
    @State private var offset: CGFloat = 1000
    
    init(text: String, firstButtonText: String, secondButtonText: String?, showButton: Binding<Bool>, shouldDismiss: Binding<Bool>, okAction: (() -> Void)? = nil) {
        self.text = text
        self.firstButtonText = firstButtonText
        self.secondButtonText = secondButtonText
        self.okAction = okAction
        _showButton = showButton
        _shouldDismiss = shouldDismiss
    }
    
    var body: some View {
        
        VStack {
            HStack {
                Spacer()
                Button {
                    showButton.toggle()
                } label: {
                    Image(systemName: "xmark")
                        .foregroundStyle(.black)
                }
            }
            Text(text)
                .dynamicTypeSize(.large)
                .multilineTextAlignment(.center)
                .padding()

            HStack {
                Button {
                    okAction?()
                    shouldDismiss.toggle()
                } label: {
                    ZStack {
                        RoundedRectangle(cornerRadius: 4)
                            .foregroundStyle(.gray)
                        Text(firstButtonText)
                            .foregroundStyle(.white)
                            .dynamicTypeSize(.medium)
                            .fontWeight(.medium)
                            .padding()
                    }
                    .padding()
                    .frame(minWidth: 0, maxWidth: 165, minHeight: 0, maxHeight: 60)
                }
                if let secondButtonText {
                    Button {
                        showButton.toggle()
                    } label: {
                        ZStack {
                            RoundedRectangle(cornerRadius: 4)
                                .foregroundStyle(.gray)
                            Text(secondButtonText)
                                .foregroundStyle(.white)
                                .dynamicTypeSize(.medium)
                                .fontWeight(.medium)
                                .padding()
                        }
                        .padding()
                        .frame(minWidth: 0, maxWidth: 165, minHeight: 0, maxHeight: 60)
                    }
                }
            }
        }
        .padding(20)
        .background(.white)
        .clipShape(RoundedRectangle(cornerRadius: 20))
        .shadow(radius: 5)
        .frame(minWidth: 0, maxWidth: 350, minHeight: 0, maxHeight: 350)
        .offset(x: 0, y: offset)
        .onAppear {
            withAnimation(.spring()) {
                offset = 0
            }
        }
    }
    
}

#Preview {
    PopUpView(text: "You have unsaved data. Are you sure you want to close without saving?",
              firstButtonText: "Ok",
              secondButtonText: "Cancel",
              showButton: .constant(true),
              shouldDismiss: .constant(false))
}
