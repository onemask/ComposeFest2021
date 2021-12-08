package com.soo.sample.JetpackComposeLayout

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.soo.sample.JetpackComposeLayout.ui.theme.JetpackComposeLayoutTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      JetpackComposeLayoutTheme {
        MyApp()
      }
    }
  }
}

@Composable
fun MyApp() {
  var shouldShowOnboarding by remember { mutableStateOf(true) }

  if (shouldShowOnboarding) {
    OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
  } else {
    Greetings()
  }
}


@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {

  Surface {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text("Welcome to the Basics Codelab!")
      Button(
        modifier = Modifier.padding(vertical = 24.dp),
        onClick = onContinueClicked
      ) {
        Text("Continue")
      }
    }
  }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
  LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
    items(items = names) { name ->
      Greeting(name = name)
    }
  }
}

@Composable
fun LayoutsCodelab() {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = "LayoutsCodelab")
        },
        actions = {
          IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = null)
          }
        }
      )
    }
  ) { innerPadding ->
    BodyContent(Modifier
      .padding(innerPadding)
      .padding(8.dp))
  }
}

@Composable
private fun CardContent(name: String) {
  var expanded by remember { mutableStateOf(false) }

  Row(
    modifier = Modifier
      .padding(12.dp)
      .animateContentSize(
        animationSpec = spring(
          dampingRatio = Spring.DampingRatioMediumBouncy,
          stiffness = Spring.StiffnessLow
        )
      )
  ) {
    Column(
      modifier = Modifier
        .weight(1f)
        .padding(12.dp)
    ) {
      Text(text = "Hello, ")
      Text(
        text = name,
        style = MaterialTheme.typography.h4.copy(
          fontWeight = FontWeight.ExtraBold
        )
      )
      if (expanded) {
        Text(
          text = ("Composem ipsum color sit lazy, " +
            "padding theme elit, sed do bouncy. ").repeat(4),
        )
      }
    }
    IconButton(onClick = { expanded = !expanded }) {
      Icon(
        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
        contentDescription = if (expanded) {
          stringResource(R.string.show_less)
        } else {
          stringResource(R.string.show_more)
        }

      )
    }
  }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
  JetpackComposeLayoutTheme {
    LayoutsCodelab()
  }
}

@Preview(
  showBackground = true,
  widthDp = 320,
  uiMode = UI_MODE_NIGHT_YES,
  name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
  JetpackComposeLayoutTheme {
    Greetings()

  }
}

@Preview
@Composable
fun PhotographerCardPreview() {
  JetpackComposeLayoutTheme {
    PhotographerCard()
  }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
  Row(modifier
    .padding(8.dp)
    .clickable(onClick = { /* Ignoring onClick */ })
    .clip(RoundedCornerShape(4.dp))
    .background(MaterialTheme.colors.surface)
    .padding(16.dp)
  ) {
    Surface(
      modifier = Modifier.size(50.dp),
      shape = CircleShape,
      color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
    ) {
      // Image goes here
    }
    Column(
      modifier = Modifier
        .padding(start = 8.dp)
        .align(Alignment.CenterVertically)
    ) {
      Text("Alfred Sisley", fontWeight = FontWeight.Bold)
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text("3 minutes ago", style = MaterialTheme.typography.body2)
      }
    }
  }
}

@Composable
fun SimpleList() {
  val scrollState = rememberScrollState()

  Column(Modifier.verticalScroll(scrollState)) {
    repeat(100) {
      Text("Item #$it")
    }
  }
}

@Composable
fun LazyList() {
  // We save the scrolling position with this state that can also
  // be used to programmatically scroll the list
  val scrollState = rememberLazyListState()

  LazyColumn(state = scrollState) {
    items(100) {
      Text("Item #$it")
    }
  }
}

@Composable
fun ImageListItem(index: Int) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Image(
      painter = rememberImagePainter(
        data = "https://developer.android.com/images/brand/Android_Robot.png"
      ),
      contentDescription = "Android Logo",
      modifier = Modifier.size(50.dp)
    )
    Spacer(Modifier.width(10.dp))
    Text("Item #$index", style = MaterialTheme.typography.subtitle1)
  }
}

@Composable
fun ScrollingList() {
  val listSize = 100
  // We save the scrolling position with this state
  val scrollState = rememberLazyListState()
  // We save the coroutine scope where our animated scroll will be executed
  val coroutineScope = rememberCoroutineScope()

  Column {
    Row {
      Button(onClick = {
        coroutineScope.launch {
          // 0 is the first item index
          scrollState.animateScrollToItem(0)
        }
      }) {
        Text("Scroll to the top")
      }

      Button(onClick = {
        coroutineScope.launch {
          // listSize - 1 is the last index of the list
          scrollState.animateScrollToItem(listSize - 1)
        }
      }) {
        Text("Scroll to the end")
      }
    }

    LazyColumn(state = scrollState) {
      items(listSize) {
        ImageListItem(it)
      }
    }
  }
}

fun Modifier.firstBaselineToTop(
  firstBaselineToTop: Dp,
) = this.then(
  layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
      // Where the composable gets placed
      placeable.placeRelative(0, placeableY)
    }
  }
)

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
  JetpackComposeLayoutTheme {
    Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
  }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
  JetpackComposeLayoutTheme {
    Text("Hi there!", Modifier.padding(top = 32.dp))
  }
}


@Composable
fun BodyContent(modifier: Modifier = Modifier) {
  Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
    StaggeredGrid {
      for (topic in topics) {
        Chip(modifier = Modifier.padding(8.dp), text = topic)
      }
    }
  }
}

@Composable
fun MyOwnColumn(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Layout(
    modifier = modifier,
    content = content
  ) { measurables, constraints ->
    // Don't constrain child views further, measure them with given constraints
    // List of measured children
    val placeables = measurables.map { measurable ->
      // Measure each child
      measurable.measure(constraints)
    }

    // Track the y co-ord we have placed children up to
    var yPosition = 0

    // Set the size of the layout as big as it can
    layout(constraints.maxWidth, constraints.maxHeight) {
      // Place children in the parent layout
      placeables.forEach { placeable ->
        // Position item on the screen
        placeable.placeRelative(x = 0, y = yPosition)

        // Record the y co-ord placed up to
        yPosition += placeable.height
      }
    }
  }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
  JetpackComposeLayoutTheme {
    OnboardingScreen(onContinueClicked = {}) // Do nothing on click.
  }
}

@Composable
fun Greeting(name: String) {
  Card(
    backgroundColor = MaterialTheme.colors.primary,
    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    {
      CardContent(name)
    }
}

val topics = listOf(
  "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
  "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
  "Religion", "Social sciences", "Technology", "TV", "Writing"
)


@Composable
fun BodyContent(modifier: Modifier = Modifier) {
  StaggeredGrid(modifier = modifier) {
    for (topic in topics) {
      Chip(modifier = Modifier.padding(8.dp), text = topic)
    }
  }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
  LayoutsCodelabTheme {
    BodyContent()
  }
}

@Composable
fun Chip(modifier: Modifier = Modifier, text: String) {
  Card(
    modifier = modifier,
    border = BorderStroke(color = Color.Black, width = Dp.Hairline),
    shape = RoundedCornerShape(8.dp)
  ) {
    Row(
      modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier.size(16.dp, 16.dp)
          .background(color = MaterialTheme.colors.secondary)
      )
      Spacer(Modifier.width(4.dp))
      Text(text = text)
    }
  }
}

@Composable
fun StaggeredGrid(
  modifier: Modifier = Modifier,
  rows: Int = 3,
  content: @Composable () -> Unit
) {
  Layout(
    modifier = modifier,
    content = content
  ) { measurables, constraints ->
    // measure and position children given constraints logic here
  }
}

@Preview
@Composable
fun ChipPreview() {
  LayoutsCodelabTheme {
    Chip(text = "Hi there")
  }
}
@Composable
fun ConstraintLayoutContent() {
  ConstraintLayout {
    // Creates references for the three composables
    // in the ConstraintLayout's body
    val (button1, button2, text) = createRefs()

    Button(
      onClick = { /* Do something */ },
      modifier = Modifier.constrainAs(button1) {
        top.linkTo(parent.top, margin = 16.dp)
      }
    ) {
      Text("Button 1")
    }

    Text("Text", Modifier.constrainAs(text) {
      top.linkTo(button1.bottom, margin = 16.dp)
      centerAround(button1.end)
    })

    val barrier = createEndBarrier(button1, text)
    Button(
      onClick = { /* Do something */ },
      modifier = Modifier.constrainAs(button2) {
        top.linkTo(parent.top, margin = 16.dp)
        start.linkTo(barrier)
      }
    ) {
      Text("Button 2")
    }
  }
}

@Preview
@Composable
fun ConstraintLayoutContentPreview() {
  LayoutsCodelabTheme {
    ConstraintLayoutContent()
  }
}

@Composable
fun LargeConstraintLayout() {
  ConstraintLayout {
    val text = createRef()

    val guideline = createGuidelineFromStart(fraction = 0.5f)
    Text(
      "This is a very very very very very very very long text",
      Modifier.constrainAs(text) {
        linkTo(start = guideline, end = parent.end)
      }
    )
  }
}

@Preview
@Composable
fun LargeConstraintLayoutPreview() {
  LayoutsCodelabTheme {
    LargeConstraintLayout()
  }
}

@Composable
fun DecoupledConstraintLayout() {
  BoxWithConstraints {
    val constraints = if (maxWidth < maxHeight) {
      decoupledConstraints(margin = 16.dp) // Portrait constraints
    } else {
      decoupledConstraints(margin = 32.dp) // Landscape constraints
    }

    ConstraintLayout(constraints) {
      Button(
        onClick = { /* Do something */ },
        modifier = Modifier.layoutId("button")
      ) {
        Text("Button")
      }

      Text("Text", Modifier.layoutId("text"))
    }
  }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
  return ConstraintSet {
    val button = createRefFor("button")
    val text = createRefFor("text")

    constrain(button) {
      top.linkTo(parent.top, margin= margin)
    }
    constrain(text) {
      top.linkTo(button.bottom, margin)
    }
  }
}
