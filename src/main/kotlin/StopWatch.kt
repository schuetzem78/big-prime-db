class StopWatch {
    private var startTime: Long = 0
    private var elapsed: Long = 0
    private var running: Boolean = false

    fun start() {
        if (!running) {
            startTime = System.nanoTime()
            running = true
        }
    }

    fun stop() {
        if (running) {
            elapsed += System.nanoTime() - startTime
            running = false
        }
    }

    fun reset() {
        elapsed = 0
        running = false
    }

    fun elapsedTimeNanos(): Long {
        return if (running) {
            elapsed + (System.nanoTime() - startTime)
        } else {
            elapsed
        }
    }

    fun elapsedTimeMillis(): Long = elapsedTimeNanos() / 1_000_000
}