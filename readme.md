Advanced Kotlin Patterns on Android /  Idiomatic Kotlin code on Android

* A [fragment](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/ankoRecyclerView/AnkoRecyclerViewFragment.kt) with a RecyclerView in Anko
  * [ViewHolder](/app/src/main/java/net/aquadc/advancedkotlinpatterns/recycler/FoodItemHolder.kt)
  * [item view](/app/src/main/java/net/aquadc/advancedkotlinpatterns/recycler/createFoodItemHolder.kt)

* Crossinline one-liner anonymous classes
  * [parcelableCreator](/app/src/main/java/net/aquadc/advancedkotlinpatterns/common/parcelable.kt#L8) function, call-sites:
    * [FoodListFragment.Mode.Popular](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/safe/FoodListFragment.kt#L68) (parcelable singleton)
    * [FoodListFragment.Mode.FilterAndSort](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/safe/FoodListFragment.kt#L78)
    * [User](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/bind/User.kt)
  * [CachedAsyncTaskLoader](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/CachedAsyncTaskLoader.kt),
  [call-site](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/LoaderFragment.kt#L59)
  * [ClickableSpan](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/ClickableSpan.kt),
  [call-site](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/LoaderFragment.kt#L64)
  * [TextWatcher](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/TextWatcher.kt),
  [call-site](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/LoaderFragment.kt#L43)

* Reactive data binding via broadcasts
  * [bind() implementation](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/bind/bind.kt)
  * [ProfileFragment](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/bind/ProfileFragment.kt#L29) — usage sample

* Safe fragments
  * [Unsafe fragment implementation](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/unsafe/FoodListFragment.kt), 
  [Unsafe call-site](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/unsafe/FoodFilterAndSortChooserFragment.kt#L54)
  * [Safe fragment](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/safe/FoodListFragment.kt)
  with deprecated no-arg ctor, a ctor-like factory, and mode delegation;
  [call-site](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/safe/FoodFilterAndSortChooserFragment.kt#L54)

* Coroutines
  * [Sample use](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/coroutines/CoroutinesFragment.kt#L50),
  including `delay` and bindings to Retrofit.

* Sealed classes
  * [sealed class Attachment](app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/sealed/example/Attachment.kt)
  * [AttachmentsFragment](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/sealed/example/AttachmentsFragment.kt)
