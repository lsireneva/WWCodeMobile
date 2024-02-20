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

    @State private var startTime = Date.now
    @State private var endTime = Date.now

    var body: some View {
        VStack(spacing: 10) {

            TopBarView()
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
        Spacer()
    }
    
    struct TopBarView: View {
        
        var body: some View {
            HStack(alignment: .lastTextBaseline, spacing: 16){
                Spacer()
                Button(action: {
                    // TODO: Add confirmation pop-up for cancel button #131
                },
                       label: {
                    Image(systemName: "trash.circle")
                        .foregroundStyle(.black)
                })
                Button(action: {
                    // TODO: Add confirmation pop-up for delete button #132
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
}

#Preview {
    DetailsScreen()
}
