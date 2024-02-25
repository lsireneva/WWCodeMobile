
import SwiftUI
import Foundation

struct CustomDatePickerView: View {
    @Binding var isPresented: Bool
    @Binding var selectedDate: Date
    
    var body: some View {
        NavigationView {
            DatePicker("Select Date", selection: $selectedDate, displayedComponents: .date)
                .datePickerStyle(GraphicalDatePickerStyle())
                .padding()
                .navigationBarItems(leading: Button("Cancel") {
                    isPresented = false
                }, trailing: Button("OK") {
                    isPresented = false
                })
        }
    }
}

