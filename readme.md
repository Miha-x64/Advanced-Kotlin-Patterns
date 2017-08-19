Advanced Kotlin Patterns in Android /  Idiomatic Kotlin code in Android

* A [fragment](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/ankoRecyclerView/AnkoRecyclerViewFragment.kt) with a RecyclerView in Anko
  * [ViewHolder](/app/src/main/java/net/aquadc/advancedkotlinpatterns/recycler/FoodItemHolder.kt)
  * [item view](/app/src/main/java/net/aquadc/advancedkotlinpatterns/recycler/createFoodItemHolder.kt)

* Crossinline one-liner anonymous classes
  * [parcelableCreator](/app/src/main/java/net/aquadc/advancedkotlinpatterns/common/parcelable.kt#L8) function, call-sites:
    * [FoodListFragment.Mode.Popular](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/safe/FoodListFragment.kt#L64) (parcelable singleton)
    * [FoodListFragment.Mode.FilterAndSort](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/safe/FoodListFragment.kt#L74)
    * [User](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/bind/User.kt)
  * [CachedAsyncTaskLoader](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/CachedAsyncTaskLoader.kt),
  [call-site](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/LoaderFragment.kt#L58)
  * [ClickableSpan](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/ClickableSpan.kt),
  [call-site](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/LoaderFragment.kt#L63)
  * [TextWatcher](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/TextWatcher.kt),
  [call-site](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/crossinlineOneLiner/LoaderFragment.kt#L42)

* Reactive data binding via broadcasts
  * [bind() implementation](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/bind/bind.kt)
  * [ProfileFragment](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/bind/ProfileFragment.kt) — usage sample

* Safe fragments
  * [Unsafe fragment implementation](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/unsafe/FoodListFragment.kt), 
  [Unsafe call-site](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/unsafe/FoodFilterAndSortChooserFragment.kt#L54)
  * [Safe fragment](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/safe/FoodListFragment.kt)
  with deprecated no-arg ctor, a ctor-like factory, and mode delegation;
  [call-site](/app/src/main/java/net/aquadc/advancedkotlinpatterns/feature/fragments/safe/FoodFilterAndSortChooserFragment.kt#L54)

* TODO: sealed classes
