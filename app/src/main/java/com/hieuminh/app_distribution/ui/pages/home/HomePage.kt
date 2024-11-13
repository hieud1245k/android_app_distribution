package com.hieuminh.app_distribution.ui.pages.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.hieuminh.app_distribution.models.Build
import com.hieuminh.app_distribution.models.Version

@Composable
fun HomePage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        DisplayList()
    }
}

@Composable
fun DisplayList() {
    var versions by remember {
        mutableStateOf(generateVersions())
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Olivia Version Release",
            modifier = Modifier.padding(10.dp),
            style = TextStyle(
                color = Color.Black,
                fontSize = TextUnit(
                    value = 20f,
                    type = TextUnitType.Sp,
                )
            ),
            fontWeight = FontWeight.W600,
        )
        LazyColumn {
            items(versions.size) { index ->
                val version = versions[index]
                ListVersionItem(version) {
                    versions = versions.mapIndexed { i, v ->
                        if (index == i) {
                            version.copy(isExpanded = !version.isExpanded)
                        } else {
                            v
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListVersionItem(
    version: Version,
    onExpandClicked: (() -> Unit)? = null,
) {
    Column {
        Row(
            modifier = Modifier.clickable {
                onExpandClicked?.invoke()
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = version.name,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = TextUnit(
                        value = 16f,
                        type = TextUnitType.Sp,
                    ),
                ),
                fontWeight = FontWeight.W600,
            )
            Icon(
                if (version.isExpanded)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                "Expand icon",
                tint = Color.Gray,
                modifier = Modifier
                    .size(48.dp)
                    .padding(10.dp),
            )
        }
        if (version.isExpanded)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                version.builds.forEach { build ->
                    ListBuildItem(build)
                }
            }
        HorizontalDivider(
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 8.dp,
                ),
        )
    }
}

@Composable
fun ListBuildItem(build: Build) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                Text(
                    text = build.title,
                    modifier = Modifier
                        .padding(vertical = 10.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit(
                            value = 14f,
                            type = TextUnitType.Sp,
                        ),
                        fontWeight = FontWeight.W500,
                    ),
                )
                Row(
                    modifier = Modifier.padding(
                        vertical = 4.dp,
                    ),
                ) {
                    Text(
                        text = "Author: ",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = TextUnit(
                                value = 12f,
                                type = TextUnitType.Sp,
                            ),
                            fontWeight = FontWeight.W400,
                        ),
                    )
                    Text(
                        text = build.author,
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = TextUnit(
                                value = 12f,
                                type = TextUnitType.Sp,
                            ),
                            fontWeight = FontWeight.W400,
                        ),
                    )
                }
            }
            Icon(
                Icons.Default.Delete,
                "Delete icon",
                tint = Color.Gray,
                modifier = Modifier
                    .size(48.dp)
                    .padding(10.dp),
            )
        }
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            Text(
                text = "Download",
                style = TextStyle(
                    color = Color.White,
                    fontSize = TextUnit(
                        value = 14f,
                        type = TextUnitType.Sp,
                    ),
                    fontWeight = FontWeight.W500,
                ),
            )
        }
    }
}

fun generateVersions(): List<Version> {
    return 9.downTo(0).map { index ->
        Version(
            name = "v1.0.$index",
            builds = List(3) {
                Build(
                    title = "Build $it",
                    url = "https://www.google.com",
                    author = "hieuminh@gmail.com",
                )
            },
            isExpanded = index == 9,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage()
}
