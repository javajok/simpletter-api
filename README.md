# API Sample

## 動かし方

```
./gradlew bootRun
```

Browse http://localhost:8080/signup

## タイムラインを取得する

* `/timeline`

```
curl http://localhost:8080/timeline
```

## つぶやく

* `/tweet`

```
curl http://localhost:8080/tweet -F userId=backpaper0 -F "text=Hello world"
```

## アイコン

* `/icon/{userId}`

```
curl -o icon.png http://localhost:8080/icon/backpaper0
```

## ライセンス

[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

