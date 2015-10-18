# API Sample

## 動かし方

```
gradlew bootRun
```

Browse http://localhost:8080/signup

## タイムラインを取得する

* `/timeline`

```
curl http://localhost:8080/timeline
```

## つぶやきをポストする

* `/post`

```
curl http://localhost:8080/post -F userId=backpaper0 -F "text=Hello world"
```

