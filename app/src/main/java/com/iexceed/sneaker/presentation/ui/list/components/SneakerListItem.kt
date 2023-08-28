package com.iexceed.sneaker.presentation.ui.list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.iexceed.sneaker.domain.model.Sneaker
import com.iexceed.sneaker.presentation.ui.list.SneakerListViewModel
import com.iexceed.sneaker.ui.theme.*

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun SneakerListItem(
    Sneakers: List<Sneaker>,
    onItemClick: (Sneaker) -> Unit,
    viewModel: SneakerListViewModel
) {
    Surface(
        modifier = Modifier
            .size(700.dp, 700.dp)
            .padding(16.dp)

    ) {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    Text(
                        text = "Popular",
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp,
                        color = black
                    )
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.background(color = white), colors = ButtonDefaults.outlinedButtonColors(backgroundColor = white)) {
                        Text(
                            text = "Sort By",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = dark_gray
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Dropdown",
                            tint = colorPrimary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(Sneakers.windowed(2, 2, true)) { sublist ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    sublist.forEach { item ->
                        Card(
                            modifier = Modifier
                                .fillParentMaxWidth(0.5f)
                                .padding(4.dp)
                                .shadow(2.dp, shape = RoundedCornerShape(16.dp)).align(Alignment.CenterVertically),
                            shape = RoundedCornerShape(16.dp),
                            backgroundColor = white,
                            onClick = {
                                onItemClick(item)
                            }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier.align(Alignment.Start)
                                        .clip(CircleShape)
                                        .background(colorPrimary)
                                        .padding(4.dp),

                                    contentAlignment = Alignment.TopStart
                                ) {
                                    Icon(
                                        modifier = Modifier.size(24.dp, 24.dp).clip(CircleShape),
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add icon",
                                        tint = white
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .size(100.dp, 100.dp)
                                        .padding(4.dp).clip(CircleShape).background(
                                            colorprimarywhite
                                        ),
                                    contentAlignment = Alignment.Center

                                ) {
                                    AsyncImage(
                                        model = item.media,
                                        contentDescription = "Sneaker",
                                        modifier = Modifier
                                            .size(84.dp, 84.dp).clip(CircleShape).padding(4.dp).align(Alignment.Center)
                                    )
                                }
                                Spacer(modifier = Modifier.height(24.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .wrapContentSize(Alignment.Center),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceAround

                                    ) {
                                        Text(
                                            text = item.name,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = black
                                        )
                                        Text(text = item.price.toString(), fontSize = 18.sp, color = black)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
