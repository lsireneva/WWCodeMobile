//
//  ListView.swift
//  wwcTracker
//
//  Created by Claudia Maciel on 1/19/24.
//

import SwiftUI

struct ListView: View {
    @ObservedObject var viewModel = DetailsViewModel()
    
    // Load activities when the view appears
    init() {
        viewModel.loadActivities()
    }
    
    var body: some View {
        NavigationView {
            List {
                // Iterate over grouped activities
                ForEach(viewModel.groupedActivities.keys.sorted(), id: \.self) { date in
                    Section(header: Text(date).bold()) {
                        // Display each activity in this group
                        ForEach(viewModel.groupedActivities[date] ?? [], id: \.id) { activity in
                            HStack {
                                Text(activity.activityName)
                                Spacer()
                                Text(activity.duration)
                            }
                        }
                    }
                }
            }
            .navigationTitle("Timer")
            .navigationBarItems(trailing: NavigationLink(destination: DetailsScreen()) {
                Image(systemName: "plus")
            })
        }
    }
}


#Preview {
    ListView()
}
