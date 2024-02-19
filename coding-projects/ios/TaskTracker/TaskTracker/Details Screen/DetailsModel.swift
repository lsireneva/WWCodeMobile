//
//  DetailsModel.swift
//  wwcTracker
//
//  Created by Claudia Maciel on 1/19/24.
//

import Foundation

struct Activity: Identifiable, Codable {
    var id = UUID()
    var activityName: String
    var date: Date
    var startTime: Date
    var endTime: Date
    
    private func formatTime(_ date: Date) -> String {
          DateFormatter.localizedString(from: date, dateStyle: .none, timeStyle: .medium)
      }
    
    // Calculate the difference between endTime and startTime and return duration
    var duration: String {
            let duration = endTime.timeIntervalSince(startTime)
            let hours = Int(duration) / 3600
            let minutes = Int(duration) / 60 % 60
            let seconds = Int(duration) % 60
            return String(format: "%02i:%02i:%02i", hours, minutes, seconds)
        }
}
