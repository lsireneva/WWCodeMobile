//
//  DetailsViewModel.swift
//  wwcTracker
//
//  Created by Claudia Maciel on 1/19/24.
//

import SwiftUI
import SwiftData
import Combine

class DetailsViewModel: ObservableObject {
    // Use `Published` property wrapper so that any changes to `task` will auto trigger the view to update
    @Published var task: Task?
    @Published var keyboardHeight: CGFloat = 0
    private var cancellables = Set<AnyCancellable>()

    init(task: Task? = nil) {
        self.task = task
        observeKeyboardChanges()
    }

    func updateTask(name: String, date: Date, startTime: Date, endTime: Date, priority: Priority) {
        // If `task` is `nil`, then nothing needs to be updated
        guard let task = task else { return }
        task.name = name
        task.date = date
        task.startTime = startTime
        task.endTime = endTime
        task.priority = priority
    }

    private func observeKeyboardChanges() {
        NotificationCenter.default.publisher(for: UIResponder.keyboardWillShowNotification)
            .map { notification in
                // Extract and return keyboard height
                (notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue)?.cgRectValue.height ?? 0
            }
            .subscribe(on: RunLoop.main)
            .assign(to: \.keyboardHeight, on: self)
            .store(in: &cancellables)

        // Listen for keyboard will hide notification
        NotificationCenter.default.publisher(for: UIResponder.keyboardWillHideNotification)
            .map { _ in CGFloat(0) }
            .subscribe(on: RunLoop.main)
            .assign(to: \.keyboardHeight, on: self)
            .store(in: &cancellables)
    }

    deinit {
        cancellables.forEach { $0.cancel() }
    }
}

