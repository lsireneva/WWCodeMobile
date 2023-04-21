# Chapter 5 - **Bend, Or Break (for April 13, 2023 )**

## Decoupling and the Law of Demeter

![https://media.giphy.com/media/ZtwEXtGMG80xWetwUb/giphy.gif](https://media.giphy.com/media/ZtwEXtGMG80xWetwUb/giphy.gif)

### Minimize Coupling

Be careful about how many other modules you interact with and how you came to interact with them.

Traversing relationships between objects directly can quickly lead to a combinatorial explosion.

Symptoms:

1. Large projects where the command to link a unit test is longer than the test program itself
2. "Simple" changes to one module that propagate through unrelated modules in the system
3. Developers who are afraid to change code because they aren't sure what might be affected

### The Law of Demeter for Functions

The Law of Demeter for functions states that any method of an object should call only methods belonging to.

> **Tip: Minimize Coupling Between Modules**
> 

### Does It Really Make a Difference?

Using The Law of Demeter will make your code more adaptable and robust, but at a cost: you will be writing a large number of wrapper methods that simply forward the request on to a delegate. 

<aside>
💡 How can you minimize coupling in your codebase, and why is this important? Do you think it's worth the cost of writing additional wrapper methods to follow this principle? Why or why not?

</aside>

Let's say you have a **`User`** class which contains a **`Profile`** object:

```swift
class User {
    var profile: Profile
    
    init(profile: Profile) {
        self.profile = profile
    }
}

class Profile {
    var name: String
    
    init(name: String) {
        self.name = name
    }
}
```

If you want to access the name of the user's profile from another class, you could do it like this:

```swift
class SomeOtherClass {
    var user: User
    
    init(user: User) {
        self.user = user
    }
    
    func getUserName() -> String {
        return user.profile.name
    }
}
```

However, this violates the Law of Demeter because **`SomeOtherClass`** is directly accessing the **`Profile`** object inside the **`User`** object. Instead, we can decouple the classes by creating a method in the **`User`** class that returns the name of the profile:

```swift
class User {
    var profile: Profile
    
    init(profile: Profile) {
        self.profile = profile
    }
    
    func getProfileName() -> String {
        return profile.name
    }
}

class SomeOtherClass {
    var user: User
    
    init(user: User) {
        self.user = user
    }
    
    func getUserName() -> String {
        return user.getProfileName()
    }
}
```

Now **`SomeOtherClass`** is no longer directly accessing the **`Profile`** object, and instead is relying on the **`User`** class to provide the necessary information.

## Metaprogramming

![https://media.giphy.com/media/5h0piMX8ku0xj97W0t/giphy.gif](https://media.giphy.com/media/5h0piMX8ku0xj97W0t/giphy.gif)

"Out with the details!" Get them out of the code. While we're at it, we can make our code highly configurable and "soft"—that is, easily adaptable to changes.

### Dynamic Configuration

Make systems highly configurable by using *metadata* to describe configuration options for an application: tuning parameters, user preferences, the installation directory, and so on.

> **Tip: Configure, Don't Integrate**
> 

### Metadata-Driven Applications

We want to configure and drive the application via metadata as much as possible. *Program for the general case, and put the specifics somewhere else —outside the compiled code base*

> **Tip: Put Abstractions in Code Details in Metadata**
> 

Benefits:

- It forces you to decouple your design, which results in a more flexible and adaptable program.
- It forces you to create a more robust, abstract design by deferring details—deferring them all the way out of the program.
- You can customize the application without recompiling it.
- Metadata can be expressed in a manner that's much closer to the problem domain than a general-purpose programming language might be.
- You may even be able to implement several different projects using the same application engine, but with different metadata.

### When to Configure

A flexible approach is to write programs that can reload their configuration while they're running.

- long-running server process: provide some way to reread and apply metadata while the program is running.
- small client GUI application: if restarts quickly no problem.

### Cooperative Configuration

Operating systems already configure themselves to hardware as they boot, and Web browsers update themselves with new components automatically.

### Don’t Write Dodo-Code

Species that don’t adapt die.

<aside>
💡 So what should we use it for in context of application development?

</aside>

## Temporal Coupling

![https://media.giphy.com/media/xT9DPNlrm5LoeKve4E/giphy.gif](https://media.giphy.com/media/xT9DPNlrm5LoeKve4E/giphy.gif)

Two aspects of time:

- Concurrency: things happening at the same time
- Ordering: the relative positions of things in time

We need to allow for concurrency and to think about decoupling any time or order dependencies. Reduce any time-based dependencies

### Workflow

Use activity diagrams to maximize parallelism by identifying activities that could be performed in parallel, but aren't.

> **Tip: Analyze Workflow to Improve Concurrency**
> 

### Architecture

Balance load among multiple consumer processes: **the hungry consumer model.**

In a hungry consumer model, you replace the central scheduler with a number of independent consumer tasks and a centralized work queue. Each consumer task grabs a piece from the work queue and goes on about the business of processing it. As each task finishes its work, it goes back to the queue for some more. This way, if any particular task gets bogged down, the others can pick up the slack, and each individual component can proceed at its own pace. Each component is temporally decoupled from the others.

> **Tip: Design Using Services**
> 

### Design for Concurrency

Programming with threads imposes some design constraints—and that's a good thing.

- Global or static variables must be protected from concurrent access
- Check if you need a global variable in the first place.
- Consistent state information, regardless of the order of calls
- Objects must always be in a valid state when called, and they can be called at the most awkward times. Use class invariants.

### Cleaner Interfaces

Thinking about concurrency and time-ordered dependencies can lead you to design cleaner interfaces as well.

> **Tip: Always Design for Concurrency**
> 

### Deployment

You can be flexible as to how the application is deployed: standalone, client-server, or n-tier.

If we design to allow for concurrency, we can more easily meet scalability or performance requirements when the time comes—and if the time never comes, we still have the benefit of a cleaner design.

<aside>
💡 How can you reduce time-based dependencies in your codebase? How can you design for concurrency in your software development work, and why is this important?

</aside>

Designing for concurrency involves breaking down tasks into smaller, independent units of work that can be executed concurrently. In Swift, you can use Grand Central Dispatch (GCD) to schedule tasks on different threads or queues. Here's an example of using GCD to download multiple images concurrently:

```swift
func downloadImages(from urls: [URL], completionHandler: @escaping ([UIImage]) -> Void) {
    let downloadQueue = DispatchQueue(label: "com.example.downloadQueue", attributes: .concurrent)
    let group = DispatchGroup()
    var images: [UIImage] = []
    
    for url in urls {
        group.enter()
        downloadQueue.async {
            do {
                let data = try Data(contentsOf: url) // This function takes time to download data from network
                if let image = UIImage(data: data) {
                    images.append(image)
                }
            } catch {
                print("Error downloading image: \(error)")
            }
            group.leave()
        }
    }
    
    group.notify(queue: .main) {
        completionHandler(images)
    }
}

// Usage:
let urls = [URL(string: "https://example.com/image1.png")!, URL(string: "https://example.com/image2.png")!]
downloadImages(from: urls) { images in
    // Display the downloaded images
}
```

In this example, the **`downloadImage`** function downloads multiple images concurrently using a concurrent queue and a dispatch group. By using multiple threads, we can download images faster and make the UI more responsive.

## It's Just a View

![https://media.giphy.com/media/3ohjV1tGg3UOW3rDEs/giphy.gif](https://media.giphy.com/media/3ohjV1tGg3UOW3rDEs/giphy.gif)

### Publish/Subscribe

Objects should be able to register to receive only the events they need, and should never be sent events they don't need.

Use publish/subscribe mechanism to implement a very important design concept: the separation of a model from views of the model.

### Model-View-Controller

Separates the model from both the GUI that represents it and the controls that manage the view.

Advantage:

- Support multiple views of the same data model.
- Use common viewers on many different data models.
- Support multiple controllers to provide nontraditional input mechanisms.

> **Tip: Separate Views from Models**
> 

### Beyond GUIs

The controller is more of a coordination mechanism and doesn't have to be related to any sort of input device.

- **Model** The abstract data model represents the target object. The model has no direct knowledge of any views or controllers.
- **View** A way to interpret the model. It subscribes to changes in the model and logical events from the controller.
- **Controller** A way to control the view and provide the model with new data. It publishes events to both the model and the view.

<aside>
💡 How can you use the publish/subscribe mechanism to improve the performance of your software applications?

</aside>

The publish/subscribe pattern is a messaging pattern that can be used to improve the performance and scalability of software applications. In this pattern, components of a system communicate indirectly through a central message broker, which manages the subscriptions and notifications. Here is some generic pseudocode to demonstrate how you can use the publish/subscribe mechanism to improve the performance of your software applications:

```swift
// Define a message broker that manages subscriptions and notifications
class MessageBroker {
    private subscribers = {}

    function subscribe(topic, callback) {
        // Register a subscriber for a topic
        if (topic not in subscribers) {
            subscribers[topic] = []
        }
        subscribers[topic].append(callback)
    }

    function unsubscribe(topic, callback) {
        // Remove a subscriber for a topic
        if (topic in subscribers) {
            subscribers[topic].remove(callback)
            if (subscribers[topic].length == 0) {
                delete subscribers[topic]
            }
        }
    }

    function publish(topic, message) {
        // Publish a message to all subscribers of a topic
        if (topic in subscribers) {
            for (callback in subscribers[topic]) {
                callback(message)
            }
        }
    }
}

// Define a publisher that sends messages to the message broker
class Publisher {
    function sendMessage(topic, message) {
        messageBroker.publish(topic, message)
    }
}

// Define a subscriber that receives messages from the message broker
class Subscriber {
    function handleEvent(message) {
        // Handle a message received from the message broker
        // ...
    }
}

// Usage:
var messageBroker = new MessageBroker()
var publisher = new Publisher()
var subscriber1 = new Subscriber()
var subscriber2 = new Subscriber()

// Subscribe subscribers to topics
messageBroker.subscribe("topic1", subscriber1.handleEvent)
messageBroker.subscribe("topic2", subscriber2.handleEvent)

// Publish messages to topics
publisher.sendMessage("topic1", "Message 1")
publisher.sendMessage("topic2", "Message 2")
```

## Blackboards

![https://media.giphy.com/media/4gO60tiRltQPe/giphy.gif](https://media.giphy.com/media/4gO60tiRltQPe/giphy.gif)

A blackboard system lets us decouple our objects from each other completely, providing a forum where knowledge consumers and producers can exchange data anonymously and asynchronously.

### Blackboard Implementations

With Blackboard systems, you can store active objects—not just data—on the blackboard, and retrieve them by partial matching of fields (via templates and wildcards) or by subtypes.

Functions that a Blackboard system should have:

- **read** Search for and retrieve data from the space.
- **write** Put an item into the space.
- **take** Similar to read, but removes the item from the space as well.
- **notify** Set up a notification to occur whenever an object is written that matches the template.

Organizing Your Blackboard by partitioning it when working on large cases.

> **Tip: Use Blackboards to Coordinate Workflow**
> 

<aside>
💡 Do you use blackboard systems in the real world— like whiteboard at work? What makes them effective? Are messages ever posted with a consistent format? Does it matter?

</aside>

Reflection Questions:
1. What is the Law of Demeter for functions and how can it help to minimize coupling? How does it work in practice?
2. In what situations is using metadata to configure applications recommended? What are some benefits of this approach?
3. What is temporal coupling and why is it important to avoid it in software development? Can you provide an example of how it can affect a program?
4. How can we make our code more adaptable and robust by avoiding temporal coupling? What techniques can we use to achieve this?
