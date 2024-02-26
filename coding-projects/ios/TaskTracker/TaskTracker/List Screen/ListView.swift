//
//  ListView.swift
//  wwcTracker
//
//  Created by Claudia Maciel on 1/19/24.
//

import SwiftUI
import SwiftData

struct ListView: View {
    @ObservedObject var viewModel: ListViewModel
    @Query var tasks: [Task]
    
    private var groupedTasks: [String: [Task]] {
        Dictionary(grouping: tasks, by: { viewModel.customFormattedDate(from: $0.date) })
    }
    
    private var sortedDates: [String] {
        groupedTasks.keys.sorted()
    }

    var body: some View {
        List {
            ForEach(sortedDates, id: \.self) { date in
                Section(header: Text(date).foregroundColor(.black)) {
                    ForEach(groupedTasks[date] ?? []) { task in
                    let duration = viewModel.formatDuration(start: task.startTime, end: task.endTime)
                    ActivityItemView(name: task.name, duration: duration)
                        .listRowSeparator(.hidden)
                    }
                }
            }
        }
        .listStyle(.plain)
    }
}

struct ActivityItemView: View {
    let name: String
    let duration: String

    var body: some View {
        HStack {
            Text(name)
            Spacer()
            Text(duration)
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
