# how to run the OTEL-collector
```shell
docker run -p 4318:4318 --volume $(pwd)/edot-otel/otel-collector-config.yaml:/etc/otelcol-contrib/config.yaml otel/opentelemetry-collector-contrib
```

