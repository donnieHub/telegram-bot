package org.vmychko

import redis.clients.jedis.Jedis

class Redis() {

    private final val host = "127.0.0.1";
    private final val port = 6379;

    public fun connect(): Jedis {
        return Jedis(host, port);
    }

    public fun start() {
        try {
            connect().use({ jedis ->
                System.out.println("Соединение установлено: " + jedis.ping())
                // Установка значения
                jedis.set("oil_price", "80.0$")
                jedis.setex("oil_price", 60,"80.0$")

                // Получение значения
                var value: String = jedis.get("oil_price")
                println("Получено: oil_price = $value")

            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}