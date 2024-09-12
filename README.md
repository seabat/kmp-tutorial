Kotlin Multiplatform App Tutorial
=================================

Kotlin Multiplatform アプリのチュートリアル集。

## KmpTutorial

#### 概要

Android/iOS をターゲットとする Kotlin Multiplatform プロジェクトを作る際のベースとして使用されることを目的としている。  
Kotlin Multiplatform の[公式チュートリアル](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-create-first-app.html) 実施後に以下のカスタマイズを加えている。

- UI は Android/iOS 側でそれぞれ実装することを前提としているため、Compose Multiplatform 要素を削除
- [ktlinter](https://github.com/jeremymailen/kotlinter-gradle)ラッパーを適用し、ktlint をコマンド実行できる
- リリースビルドで [ProGuard/R8](https://developer.android.com/build/shrink-code?hl=ja)  が有効になる
- shared モジュールに Android の [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ja) ライクなクラスを追加
- shared モジュールに [Android 推奨アーキテクチャー](https://developer.android.com/topic/architecture?hl=ja)の UseCase・Repository・DataSource クラスを追加
- [Koin](https://insert-koin.io/) で DI する
- Unit テストのリファレンスコードを追加

#### スクリーンショット

<img src="https://github.com/user-attachments/assets/50e24aad-ef37-4db7-8980-43d86277a51a" alt="Androidのスクショ" width="250px"> <img src="https://github.com/user-attachments/assets/92c95083-85b1-4040-b2f7-b304c0b86e27" alt="iOSのスクショ" width="250px"> 


