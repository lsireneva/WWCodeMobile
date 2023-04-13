//
//  ContentView.swift
//  BookShelf
//
//  Created by Peter Friese on 11.09.22.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import SwiftUI

struct BookListView: View {
    var books: [Book]
  var body: some View {
      List(books) { book in
          BookRowView(book: book)
      }
      .listStyle(.plain)
  }
}

struct BookListView_Previews: PreviewProvider {
  static var previews: some View {
      BookListView(books: Book.sampleBooks)
          
  }
}

struct BookRowView: View {
    var book: Book
    var body: some View {
        HStack(alignment:.top) {
            Image(book.mediumCoverImageName)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(height:90)
            VStack(alignment: .leading) {
                
                Text(book.title)
                    .font(.headline)
                Text(book.author)
                    .font(.subheadline)
                HStack(alignment: .top) {
                    
                    Text("\(book.pages)")
                        .font(.subheadline)
                    Text("Pages")
                        .font(.subheadline)
                    Spacer()
                    Text("ISBN:").font(.subheadline).bold()
                    Text("\(book.isbn)")
                        .font(.subheadline)
                }
                
                
            }
            Spacer()
        }
        
    }
}
