package org.vmychko

import redis.clients.jedis.Jedis

public fun main() {
    println("Hello World!")
    val redis = Redis();
    redis.start();
}