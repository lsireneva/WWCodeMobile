//
//  DetailsScreen.swift
//  wwcTracker
//
//  Created by Claudia Maciel on 1/19/24.
//

import SwiftUI

struct DetailsScreen: View {
    @Environment(\.dismiss) var dismiss
    @ObservedObject var viewModel = DetailsViewModel()
    @State private var taskText: String = ""
    @State private var shouldDismiss: Bool = false
    @State private var taskDate = Date.now
    @State private var showDeleteConfirmationPopup: Bool = false
    @State private var showCancelConfirmationPopup: Bool = false
    @State private var startTime = Date.now
    @State private var endTime = Date.now

    let task: Task?

    var body: some View {
        ZStack {
            VStack(spacing: 10) {
                
                TopBarView(showDeleteConfirmationPopup: $showDeleteConfirmationPopup,
                           showCancelConfirmationPopup: $showCancelConfirmationPopup)
                
                DatePicker(selection: $taskDate, displayedComponents: .date) {
                    LeftTitleText(text: "Date")
                }
                .datePickerStyle(.compact)
                .padding([.leading, .trailing])
                
                TextField("Enter your task", text: $taskText)
                    .padding()
                    .frame(minWidth: 0, maxWidth: 300, minHeight: 0, maxHeight: 200, alignment: .topLeading)
                    .border(.secondary)
                
                DatePicker(selection: $startTime, displayedComponents: .hourAndMinute) {
                    LeftTitleText(text: "Start Time")
                }
                .datePickerStyle(.compact)
                .padding([.leading, .trailing])
                
                DatePicker(selection: $endTime, displayedComponents: .hourAndMinute) {
                    LeftTitleText(text: "End Time")
                }
                .datePickerStyle(.compact)
                .padding([.leading, .trailing])
                
                Spacer()
                
                DoneButton(shouldDismiss: $shouldDismiss)
                
                Spacer()
            }
            .padding()
            .overlay(
                RoundedRectangle(cornerRadius: 25)
                    .stroke(lineWidth: 3)
                    .foregroundStyle(.black))
            .padding()
            .cornerRadius(25)
            .onChange(of: shouldDismiss) {
                if shouldDismiss {
                    dismiss()
                }
            }
            .onAppear {
                taskText = task?.name ?? ""
                taskDate = task?.date ?? Date()
                startTime = task?.startTime ?? Date.now
                endTime = task?.endTime ?? Date.now
            }
            // overlay for the whole screen
            .overlay(
                ZStack {
                    if showDeleteConfirmationPopup || showCancelConfirmationPopup {
                        Color.black.opacity(0.5)
                            .edgesIgnoringSafeArea(.all)
                            .onTapGesture {
                                showDeleteConfirmationPopup = false
                                showCancelConfirmationPopup = false
                            }
                    }
                }
            )
            
            // show popup on the whole screen
            if $showDeleteConfirmationPopup.wrappedValue {
                DeleteConfirmationPopupView(showDeleteConfirmationPopup: $showDeleteConfirmationPopup)
            }
            
            if $showCancelConfirmationPopup.wrappedValue {
                PopUpView(text: "You have unsaved data. Are you sure you want to close without saving?",
                          firstButtonText: "Ok",
                          secondButtonText: "Cancel",
                          showButton: $showCancelConfirmationPopup,
                          shouldDismiss: $shouldDismiss)
            }
        }
        Spacer()
    }
    
    struct TopBarView: View {
        @Binding var showDeleteConfirmationPopup: Bool
        @Binding var showCancelConfirmationPopup: Bool
        
        var body: some View {
            HStack(alignment: .lastTextBaseline, spacing: 16){
                Spacer()
                Button(action: {
                    showDeleteConfirmationPopup = true
                },
                       label: {
                    Image(systemName: "trash.circle")
                        .foregroundStyle(.black)
                })
                Button(action: {
                    showCancelConfirmationPopup = true
                },
                       label: {
                    Image(systemName: "x.circle.fill")
                        .foregroundStyle(.red)
                })
            }
            .font(.largeTitle)
            .padding()
        }
    }
    
    struct LeftTitleText: View {
        var text: String
        
        var body: some View {
            Text(text)
                .textCase(.uppercase)
                .font(.title)
                .fontWeight(.medium)
                .lineLimit(1)
                .minimumScaleFactor(0.5)
        }
    }
    
    struct DoneButton: View {
        @Binding var shouldDismiss: Bool
        
        var body: some View {
            Button("Done") {
                shouldDismiss = true
            }
            .frame(minWidth: 0, maxWidth: 200)
            .font(.system(size: 18))
            .padding()
            .foregroundColor(.green)
            .overlay(
                RoundedRectangle(cornerRadius: 25)
                    .stroke(lineWidth: 3)
                    .foregroundStyle(.green))
            .cornerRadius(25)
        }
    }
    
    private struct DeleteConfirmationPopupView: View {
        @Environment(\.presentationMode) var presentationMode
        @Binding var showDeleteConfirmationPopup: Bool
        
        var body: some View {
            ZStack {
                VStack {
                    Text("Are you sure you want to delete this entry?")
                        .multilineTextAlignment(.center)
                        .font(.title2)
                        .padding()
                    
                    HStack {
                        Button("Ok") {
                            // TODO: Delete Timed Activity #80
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
}

#Preview {
    DetailsScreen()
}
