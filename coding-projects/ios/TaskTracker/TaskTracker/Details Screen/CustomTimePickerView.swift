

import Foundation
import SwiftUI

struct CustomTimePickerView: View {
    @Binding var isPresented: Bool
    @Binding var selectedTime: Date
    
    var body: some View {
        NavigationView {
            DatePicker("Select Time", selection: $selectedTime, displayedComponents: .hourAndMinute)
                .datePickerStyle(WheelDatePickerStyle())
                .padding()
                .navigationBarItems(leading: Button("Cancel") {
                    isPresented = false
                }, trailing: Button("OK") {
                    isPresented = false
                })
        }
    }
}
