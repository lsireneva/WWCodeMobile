//
//  ListView.swift
//  wwcTracker
//
//  Created by Claudia Maciel on 1/19/24.
//

import SwiftUI
import SwiftData

struct ListView: View {

    // MARK: Private properties

    @State private var deletedTask: Task?
    @State private var showDeleteConfirmationPopup: Bool = false

    private var groupedTasks: [String: [Task]] {
        Dictionary(grouping: tasks, by: { viewModel.customFormattedDate(from: $0.date) })
    }

    private var sortedDates: [String] {
        groupedTasks.keys.sorted().reversed()
    }

    // MARK: Public properties

    @ObservedObject var viewModel: ListViewModel
    @Query var tasks: [Task]

    // MARK: LifeCycle

    var body: some View {
        NavigationStack {
            List {
                ForEach(sortedDates, id: \.self) { date in
                    Section(header: Text(date).foregroundColor(.black)) {
                        ForEach(groupedTasks[date] ?? [], id: \.id) { task in
                            ZStack {
                                let duration = viewModel.formatDuration(start: task.startTime, end: task.endTime)
                                ActivityItemView(name: task.name, duration: duration, priority: task.priority.rawValue)
                                    .listRowSeparator(.hidden)

                                NavigationLink(destination: DetailsScreen(task: task, isEditingMode: true), label: {})
                                    .opacity(0) // Hides the NavigationLink visually but retains its functionality
                            }
                            .listRowSeparator(.hidden)
                        }
                        .onDelete { indexSet in // Delete action for task
                            deleteTask(at: indexSet, taskDate: date)
                        }
                    }
                }
            }
            .listStyle(.plain)
        }
        .overlay(
            ZStack {
                if let unwrappedTask = deletedTask, showDeleteConfirmationPopup {
                    Color.black.opacity(0.5)
                        .edgesIgnoringSafeArea(.all)
                        .onTapGesture {
                            showDeleteConfirmationPopup = false
                        }
                    DeleteConfirmationPopupView(showDeleteConfirmationPopup: $showDeleteConfirmationPopup, task: unwrappedTask)
                }
            }
        )
    }
}

extension ListView {

    /// Delete a task
    ///
    ///- Parameters:
    ///   - offsets: The index set representing the offsets of tasks to be deleted.
    ///   - taskDate: The date of the tasks to be deleted.
    ///
    /// Iterates through the elements and retrieves the task for deletion,
    /// then displays a popup message for confirmation.
    private func deleteTask(at offsets: IndexSet, taskDate: String) {
        for i in offsets.makeIterator() {
            if let tasks = groupedTasks[taskDate]{
                deletedTask = tasks[i]
                showDeleteConfirmationPopup = true
            }
        }
    }
}

struct ActivityItemView: View {

    // MARK: Public properties

    let name: String
    let duration: String
    let priority: String

    // MARK: LifeCycle

    var body: some View {
        HStack(alignment: .top) {
            Text(name)
            Spacer()
            VStack(alignment: .trailing) {
                Text(duration)
                Text(priority)
                    .bold()
                    .font(.caption2)
                    .padding(8)
                    .background(Color.purple)
                    .cornerRadius(8)
                    .foregroundColor(.white)
            }
        }
        .padding()
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(.gray, lineWidth: 2)
        )
    }
}


#Preview {
    ListView(viewModel: ListViewModel())
        .modelContainer(taskListPreviewContainer)
}
