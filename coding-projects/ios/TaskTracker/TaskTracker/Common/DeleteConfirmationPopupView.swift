//
//  DeleteConfirmationPopupView.swift
//  TaskTracker
//
//  Created by P993855 on 11/03/2024.
//

import SwiftUI

struct DeleteConfirmationPopupView: View {
    @Environment(\.modelContext) var modelContext
    @Environment(\.presentationMode) var presentationMode
    @Binding var showDeleteConfirmationPopup: Bool
    let task: Task

    var body: some View {
        ZStack {
            VStack {
                Text("Are you sure you want to delete this entry?")
                    .multilineTextAlignment(.center)
                    .font(.title2)
                    .padding()

                HStack {
                    Button("Ok") {
                        modelContext.delete(task)
                        self.showDeleteConfirmationPopup = false
                        presentationMode.wrappedValue.dismiss()
                    }
                    .frame(minWidth: 0, maxWidth: 200)
                    .font(.system(size: 20))
                    .padding()
                    .background(.gray)
                    .foregroundColor(.white)
                    .cornerRadius(5)


                    .padding()

                    Button("Cancel") {
                        self.showDeleteConfirmationPopup = false
                    }
                    .frame(minWidth: 0, maxWidth: 200)
                    .font(.system(size: 18))
                    .padding()
                    .background(.gray)
                    .foregroundColor(.white)
                    .cornerRadius(5)
                    .padding()
                }
            }
            .background(Color.white)
        }
        .frame(width: 300, height: 200)
        .cornerRadius(20)
        .shadow(radius: 20)

    }
}
