# Simpletter API

ほんのりTwitter風のAPIです。
[クライアントアプリの参照実装](https://github.com/javajok/simpletter)もあります。

## 動かし方

IDEから `Application` を実行する、またはGradleで動かす事ができます。

```
./gradlew bootRun
```

コンソールに

> Started Application in 9.005 seconds (JVM running for 9.565)

といった感じのログが出力されたら正常に起動しています。

Webブラウザで次のURLを開いてみてください。

* http://localhost:8090/signup

## APIリファレンス

### タイムラインを取得する

投稿されたつぶやきの一覧を取得します。

* `/timeline`

```
curl http://localhost:8090/timeline
```

### つぶやく

つぶやきを投稿します。

* `/tweet`

```
curl http://localhost:8090/tweet -F userId=backpaper0 -F "text=Hello world"
```

### アイコン

ユーザーのアイコンを取得します。

* `/icon/{userId}`

```
curl -o icon.png http://localhost:8090/icon/backpaper0
```

## ライセンス

[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

