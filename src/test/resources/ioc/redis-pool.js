var ioc = {
	jedisPoolConfig : {
		type : "redis.clients.jedis.JedisPoolConfig",
		fields : {
			testWhileIdle : true,
			maxTotal : 100
		}
	},
	shardedJedisPool : {
		type : "redis.clients.jedis.ShardedJedisPool",
		args : [ {
			refer : "jedisPoolConfig"
		}, [ {
			type : "redis.clients.jedis.JedisShardInfo",
			args : [ {
				java : "$conf.get('redis.host', 'localhost')"
			}, 6379, "instance:01" ]
		} ] ],
		events : {
			depose : "destroy"
		}
	},
	autoLoadConfig : {
		type : "com.jarvis.cache.to.AutoLoadConfig",
		fields : {
			threadCnt : 10,
			maxElement : 20000,
			printSlowLog : true,
			slowLoadTime : 500,
			sortType : 1,
			checkFromCacheBeforeLoad : true,
			autoLoadPeriod : 50
		}
	},
	hessianSerializer : {
		type : "com.jarvis.cache.serializer.HessianSerializer"
	},
	cachePointCut : {
		type : "com.jarvis.cache.map.CachePointCut",
		args : [ {
			refer : "autoLoadConfig"
		} ],
		fields : {
			serializer : {
				refer : "hessianSerializer"
			},
			namespace : 'test_hessian'
		},
		events : {
			depose : "destroy"
		}
	}
};