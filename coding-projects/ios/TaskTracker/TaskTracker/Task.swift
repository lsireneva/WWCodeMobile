//
//  Task.swift
//  TaskTracker
//
//  Created by Jancy on 2/12/24.
//

import Foundation
import SwiftData

@Model
class Task {
    
    var name: String
    var date: Date
    var startTime: Date
    var endTime: Date
    var priority: Priority

    init(name: String = "", date: Date = .now, startTime: Date = .now, endTime: Date, priority: Priority) {
        self.name = name
        self.date = date
        self.startTime = startTime
        self.endTime = endTime
        self.priority = priority
    }
    // Calculate the duration between startTime and endTime
    var duration: String {
        let duration = endTime.timeIntervalSince(startTime)
        let hours = Int(duration) / 3600
        let minutes = Int(duration) / 60 % 60
        let seconds = Int(duration) % 60
        return String(format: "%02i:%02i:%02i", hours, minutes, seconds)
    }
}
