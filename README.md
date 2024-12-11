Kotlin Multiplatform App Tutorial
=================================

Kotlin Multiplatform アプリのチュートリアル集。

## KmpTutorial

#### 概要

Android/iOS をターゲットとする Kotlin Multiplatform プロジェクトを作る際のベースとして使用されることを目的としている。  
Kotlin Multiplatform の[公式チュートリアル](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-create-first-app.html) 実施後に以下のカスタマイズを加えている。

##### Compose Multiplatform 要素を削除

[Kotlin Multiplatform Wizard](https://kmp.jetbrains.com/?_gl=1*1bk2vxi*_gcl_au*MTE4ODEwMDk0OS4xNzMyNDMxOTE4*FPAU*MTE4ODEwMDk0OS4xNzMyNDMxOTE4*_ga*MTg4MzY4NzgyNC4xNjgzOTY0MzAx*_ga_9J976DJZ68*MTczMzkyMDAwNC4xMjcuMC4xNzMzOTIwMDA4LjU2LjAuMA..) で 「Do not share UI (use only SwiftUI)」を選択してプロジェクトを作成性た場合でも、 Compose Multiplatform 要素が含まれてしまう。UI は Android/iOS 側でそれぞれ実装したいので、Compose Multiplatform 要素を削除した。

##### ktlinter の導入

[ktlinter](https://github.com/jeremymailen/kotlinter-gradle)ラッパーを適用した。
以下の ktlint をコマンド実行すると composeApp モジュールと shared モジュールに lint を実行できる。

``` shell
# lint 実行
./gradlew lintKotlin 

# フォーマッター実行
./gradlew formatKotlin 
```

##### ProGuard/R8 の適用

リリースビルドで [ProGuard/R8](https://developer.android.com/build/shrink-code?hl=ja) が適用される。

``` kotlin
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
```

##### shared モジュールに Android の [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ja) ライクなクラスを追加

```kotlin
class GreetingSharedViewModel :
    CoroutineViewModel(),
    KoinComponent {
    private val createPhrasesUseCase: CreatePhrasesUseCaseContract by inject()

    private val _phrases = MutableStateFlow<List<String>>(emptyList())
    val phrases: StateFlow<List<String>> = _phrases

    /**
     * フレーズを読み込む (Android 向け)
     *
     * - Android は coroutine で取得した値を StateFlow で受け取る
     */
    fun loadPhrases() =
        coroutineScope.launch {
            _phrases.update {
                createPhrasesUseCase.invoke()
            }
        }

    /**
     * フレーズの監視を開始する (iOS 向け)
     *
     * - iOS は StateFlow を扱えないので coroutine で取得した値をクロージャの中で受け取る
     *
     * @param onChange
     */
    fun observePhrases(onChange: (List<String>) -> Unit) {
        coroutineScope.launch {
            onChange(
                createPhrasesUseCase.invoke()
            )
        }
    }
}
```

Android 側から shared に定義した ViewModel を参照する。

```kotlin
@Composable
fun GreetingScreen() {
    val greetingSharedViewModel: GreetingSharedViewModel = viewModel()
 (省略)   
 ```   

##### shared モジュールに [Android 推奨アーキテクチャー](https://developer.android.com/topic/architecture?hl=ja)の UseCase・Repository・DataSource クラスを追加

ViewModel から UseCase を参照。

```kotlin
class GreetingSharedViewModel :
    CoroutineViewModel(),
    KoinComponent {
    private val createPhrasesUseCase: CreatePhrasesUseCaseContract by inject()
 ```   

UseCase から Repository を参照。

```kotlin
class CreatePhrasesUseCase(private val platformRepository: PlatformRepositoryContract) :
    CreatePhrasesUseCaseContract {
    override suspend fun invoke(): List<String> = buildList {
        add("Hello, ${platformRepository.getPlatformName()}!")
        add(daysPhrase())
    }
}
```

Repository から DataSource を参照。

```kotlin
class PlatformRepository(private val platformSource: PlatformSourceContract) :
    PlatformRepositoryContract {
    override fun getPlatformName(): String = platformSource.getPlatformName()
}
```

##### Koint を導入

[Koin](https://insert-koin.io/) で DI する

#####  Unit テストのリファレンスコードを追加

UseCase の Unit テスト。

```kotlin
class CreatePhrasesUseCaseTest {
    private lateinit var useCase: CreatePhrasesUseCaseContract

    @Test
    fun testCreatePhrasesUseCase() {
        useCase = CreatePhrasesUseCase(FakeRecipeRepository())

        runTest {
            val phrases = useCase()
            assertEquals(phrases.size, 2)
            assertEquals(true, phrases[0].startsWith("Hello, Android 34!"))
            assertEquals(true, phrases[1].startsWith("There are only"))
        }
    }
}
```

Repository の Unit テスト。

```kotlin
class PlatformRepositoryTest {
    @Test
    fun testGetPlatformName() {
        val repository = PlatformRepository(FakePlatformSource())
        val platformName = repository.getPlatformName()
        assertEquals(true, platformName.isNotEmpty())
    }
}
```

#### スクリーンショット

Android

<img src="KmpTutorial/docs/android_home.png" width="200"> <img src="KmpTutorial/docs/android_greeting.png" width="200"> <img src="KmpTutorial/docs/android_grep.png" width="200"> <img src="KmpTutorial/docs/android_rocket_launch.png" width="200">  
  
iOS

<img src="https://github.com/user-attachments/assets/92c95083-85b1-4040-b2f7-b304c0b86e27" alt="iOSのスクショ" width="250px"> 


## CmpTutorial

#### 概要

Android/iOS をターゲットとする Compose Multiplatform プロジェクトを作る際のベースとして使用されることを目的としている。  
Compose Multiplatform の[公式チュートリアル](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html) 実施後に以下のカスタマイズを加えている。

- composeApp/src/commonMain/ にある Greeting#greet を iOS プロジェクトから呼び出す

#### スクリーンショット

https://github.com/user-attachments/assets/b90d888b-100e-499e-977f-a2e1e9039ce1 

https://github.com/user-attachments/assets/dc5ef4d3-b018-4138-8dbf-ae37699135f1



