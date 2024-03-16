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
        groupedTasks.keys.sorted().reversed()
    }

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
                    }
                }
            }
            .listStyle(.plain)
        }
    }
}

struct ActivityItemView: View {
    let name: String
    let duration: String
    let priority: String

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
