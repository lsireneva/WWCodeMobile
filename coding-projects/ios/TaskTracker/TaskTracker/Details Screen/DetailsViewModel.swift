//
//  DetailsViewModel.swift
//  wwcTracker
//
//  Created by Claudia Maciel on 1/19/24.
//

import Foundation

class DetailsViewModel: ObservableObject {
    // Use `Published` property wrapper so that any changes to `task` will auto trigger the view to update
    @Published var task: Task?
    
    init(task: Task? = nil) {
        self.task = task
    }
    
    func updateTask(name: String, date: Date, startTime: Date, endTime: Date) {
        // If `task` is `nil`, then nothing needs to be updated
        guard let task = task else { return }
        task.name = name
        task.date = date
        task.startTime = startTime
        task.endTime = endTime
    }
}

