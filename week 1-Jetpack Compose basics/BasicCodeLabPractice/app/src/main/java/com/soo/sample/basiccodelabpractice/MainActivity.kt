package com.soo.sample.basiccodelabpractice

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soo.sample.basiccodelabpractice.ui.theme.BasicCodeLabPracticeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BasicCodeLabPracticeTheme {
        MyApp()
      }
    }
  }
}

@Composable
fun MyApp() {
  var shouldShowOnboarding = rememberSaveable { mutableStateOf(true) }
  if (shouldShowOnboarding.value) {
    OnboardingScreen(onContinueClicked = { shouldShowOnboarding.value = false })
  } else {
    Greetings()
  }
}

@Composable
private fun Greeting(name: String) {
  var expanded by remember { mutableStateOf(false) }

  val extraPadding by animateDpAsState(
    if (expanded) 48.dp else 0.dp
  )
  Surface(
    color = MaterialTheme.colors.primary,
    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
  ) {
    Row(modifier = Modifier.padding(24.dp)) {
      Column(modifier = Modifier
        .weight(1f)
        .padding(bottom = extraPadding)
      ) {
        Text(text = "Hello, ")
        Text(text = name)
      }
      OutlinedButton(
        onClick = { expanded = !expanded }
      ) {
        Text(if (expanded) "Show less" else "Show more")
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

@Preview(
  showBackground = true,
  widthDp = 320,
  uiMode = UI_MODE_NIGHT_YES,
  name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
  BasicCodeLabPracticeTheme {
    MyApp()
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

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
  // TODO: This state should be hoisted
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

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
  BasicCodeLabPracticeTheme {
    OnboardingScreen(onContinueClicked = {}) // Do nothing on click.
  }
}
