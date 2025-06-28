# ParallelProgrammingJava

This project demonstrates basic parallel programming in Java using:

- ✅ Fork/Join framework (`RecursiveTask`)
- ✅ `ExecutorService` with `Callable`
- ✅ Proper shutdown using `try-with-resources`
- ✅ Clean thread management

 **Make sure you're using Java 9 or later.**

| Technique                    | Threads Used                  | Setup Required       | When to Use                                   | Example Class                      |
| ---------------------------- | ----------------------------- | -------------------- | --------------------------------------------- | ---------------------------------- |
| `ForkJoin` (`RecursiveTask`) | ForkJoinPool (default/common) | Custom class, pool   | Recursive splitting tasks, divide-and-conquer | `ASumExample`                      |
| `ExecutorService`            | Manually created thread pool  | Explicit thread mgmt | Precise control over threads & task batching  | `ParallelSumExecutor`              |
| `CompletableFuture`          | ForkJoinPool (by default)     | Lightweight          | Async computation, pipelines, chaining tasks  | `ParallelSumWithCompletableFuture` |
| `parallelStream()`           | ForkJoinPool (common pool)    | Minimal              | Parallel processing of collection-based data  | `ParallelSumWithStreams`           |

✅ Summary of Benefits
ForkJoin: Best for recursive divide-and-conquer tasks (e.g., sorting, tree processing).

ExecutorService: Gives full control over number of threads and lifecycle.

CompletableFuture: Best for async flows and chaining logic.

parallelStream(): Best for simple parallelism over collections with minimal boilerplate.

