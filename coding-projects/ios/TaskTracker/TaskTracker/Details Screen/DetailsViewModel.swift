//
//  DetailsViewModel.swift
//  wwcTracker
//
//  Created by Claudia Maciel on 1/19/24.
//

import Foundation
class DetailsViewModel: ObservableObject {
    @Published var groupedActivities: [String: [Activity]] = [:]
    private let activitiesKey = "savedActivities"
        
    init() {
        loadActivities()
    }
        
    func addActivity(name: String, date: Date, startTime: Date, endTime: Date) {
        let newActivity = Activity(activityName: name, date: date, startTime: startTime, endTime: endTime)
            
        // Fetch existing activities
        var activities = fetchActivities()
                    
        // Append new activity
            activities.append(newActivity)
                    
        // Encode and save the updated activities array
        if let encoded = try? JSONEncoder().encode(activities) {
            UserDefaults.standard.set(encoded, forKey: activitiesKey)
        }
            
        // Reload activities to update UI
        loadActivities()
            
        print("Activity \(name) added")
        }
        
    private func fetchActivities() -> [Activity] {
        if let savedActivities = UserDefaults.standard.data(forKey: activitiesKey),
            let decodedActivities = try? JSONDecoder().decode([Activity].self, from: savedActivities) {
            return decodedActivities
        }
        return []
    }
        
    func loadActivities() {
        let activities = fetchActivities()
            
        // Group activities by customFormattedDate
        groupedActivities = Dictionary(grouping: activities) { activity in
            customFormattedDate(from: activity.date)
        }
    }

    func customFormattedDate(from date: Date) -> String {
        let calendar = Calendar.current
        let dateFormatter = DateFormatter()
            
        if calendar.isDateInToday(date) {
            return "Today"
        } else if calendar.isDateInYesterday(date) {
            return "Yesterday"
        } else {
            dateFormatter.dateFormat = "MMMM dd, EEEE"
            return dateFormatter.string(from: date)
        }
    }
}
