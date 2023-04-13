# Notes for Unit 4

# 📚Resources for **Android Basics in Kotlin: Unit 4**

### 🔗 Unit 4 Link:

This unit is based on  [https://developer.android.com/courses/android-basics-kotlin/unit-4](https://www.notion.so/Resources-for-Android-Basics-in-Kotlin-26181ba7ec654fda817f7e4238362d75)

## 📝 NOTES:

Learn to write coroutines for complex code, and learn about HTTP and REST to get data from the internet.

We have 2 ‘pathways’ for this unit, each with interactive coding portions as follows:

- Learn how to use Kotlin Coroutines to perform long running tasks in an asynchronous non blocking way.
- Learn how to use Retrofit(a third party library) to retrieve and upload data via a REST-based web service. You will also learn how to load and display images from a web URL using Coil library.

### 💡 Tips for learners:

<aside>
👉🏽 Research more on coroutines to get a deeper understanding. You can browse on [Google codelabs](https://codelabs.developers.google.com/) website.

</aside>

<aside>
👉🏽 After finishing the unit, create an app that fetches data from an Api and displays the data to the user. You will be building on top of this app in the coming weeks as we progress in the journey. Here is a list of different API you can use: [Public Apis](https://github.com/public-apis/public-apis)

</aside>

## 🤔 Reflection Questions:

- As an Android developer, what are some of the benefits of using coroutines over working with threads directly?
- Can you explain the purpose of the async() function in coroutines, and how it is used to create asynchronous tasks that can hold a reference to a future value?
- How do you use binding adapters in your apps? Can you provide an example of a custom behavior you've implemented using binding adapters?
- What is your experience with RecyclerView and GridLayoutManager? How do you handle updates to the list of properties when it changes?
- What is your experience with debugging using breakpoints? Have you ever used the Android Debug Bridge (ADB) to attach the debugger to an app?

## 📚 Additional resources:

### Coroutines

- [https://reflectoring.io/understanding-kotlin-coroutines-tutorial/](https://reflectoring.io/understanding-kotlin-coroutines-tutorial/)
- [https://medium.com/kayvan-kaseb/understanding-kotlin-coroutines-e0a4368339b0](https://medium.com/kayvan-kaseb/understanding-kotlin-coroutines-e0a4368339b0)
- [https://www.lukaslechner.com/understanding-kotlin-coroutines-with-this-mental-model/](https://www.lukaslechner.com/understanding-kotlin-coroutines-with-this-mental-model/)
- [https://www.simplilearn.com/tutorials/kotlin-tutorial/ultimate-guide-on-kotlin-coroutines](https://www.simplilearn.com/tutorials/kotlin-tutorial/ultimate-guide-on-kotlin-coroutines)
- [WHAT IS A COROUTINE? - Kotlin Coroutines](https://youtu.be/ShNhJ3wMpvQ)
- [Kotlin Coroutines 101 - Android Conference Talks](https://youtu.be/ZTDXo0-SKuU)
- [The ABC of Coroutines - Kotlin Vocabulary](https://youtu.be/bM7PVVL_5GM)
- [The ABC of Coroutines - Kotlin Vocabulary](https://youtu.be/bM7PVVL_5GM)

### Fetching data from the internet

- [Retrieve Data From API in Android Studio Fully Explained](https://youtu.be/pKr0DVoZsEw)
- [Android Kotlin: How to Fetch Data From API display in RecyclerView Android Studio | Coding Delivery](https://youtu.be/4o6QwVe_2Yg)

## 👔  Interview Questions:

- Why do we need to use concurrency?
- Is GlobalScope recommended? Why? Or Why not?
- What is the difference between a suspend function and a regular function?
- why do we use Dispatchers
- Can you describe the MVVM architecture?
- What are android permissions and where do we add them?
- how do we load images from a network call?
- Follow up - what are some benefits of one library vs the other (Glide vs coil etc.)
- could you talk about how you debug an app?
- how and why do we parse jsons?
