package com.iexceed.sneaker.presentation.ui.checkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.iexceed.sneaker.domain.model.SneakerCart
import com.iexceed.sneaker.presentation.ui.checkout.SneakerCartEvent
import com.iexceed.sneaker.presentation.ui.checkout.SneakerCartViewModel
import com.iexceed.sneaker.ui.theme.*
import java.util.*

@Composable
fun CartListItem(
    Sneakers: List<SneakerCart>,
    viewModel: SneakerCartViewModel,
    onBack: () -> Unit
) {
    val iconSize = 24.dp
    val offsetInPx = LocalDensity.current.run { (iconSize / 2).roundToPx() }

    LazyColumn() {
        item {
            HeaderCartItems({ onBack() })
        }
        if (Sneakers.isNotEmpty()) {
            items(Sneakers) { item ->

                Column(modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(0.3f), verticalArrangement = Arrangement.SpaceAround) {
                    Box(
                        modifier = Modifier.padding((iconSize / 2)).align(Alignment.End),

                        contentAlignment = Alignment.TopEnd

                    ) {
                        IconButton(
                            onClick = { viewModel.onEvent(SneakerCartEvent.DeleteSneaker(item)) },
                            modifier = Modifier
                                .offset {
                                    IntOffset(x = +offsetInPx, y = -offsetInPx)
                                }
                                .clip(CircleShape)
                                .background(Color.White)
                                .size(iconSize)

                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "",
                                tint = colorPrimary
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding((iconSize / 2))
                            .shadow(2.dp, shape = RoundedCornerShape(16.dp))
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = white

                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = item.image,
                                contentDescription = "Sneaker",
                                modifier = Modifier
                                    .size(84.dp, 84.dp)
                                    .clip(CircleShape)
                                    .padding(4.dp)

                            )
                            Column(
                                modifier = Modifier
                                    .wrapContentSize(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly

                            ) {
                                Text(
                                    text = "${item.name} -${item.brand}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = black
                                )
                                Text(text = "$ ${item.price}", fontSize = 20.sp, color = dark_gray)
                            }
                        }
                    }
                }
            }
        } else {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "No items in cart",
                        color = colorPrimary,
                        modifier = Modifier.padding(end = 150.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CheckoutScreen(Sneakers: List<SneakerCart>, viewModel: SneakerCartViewModel, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            CartListItem(Sneakers, viewModel, { onBack() })
            Spacer(modifier = Modifier.padding(10.dp))
            CartDetails(viewModel)
        }
    }
}

@Composable
fun HeaderCartItems(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBack() }) {
            Icon(
                modifier = Modifier.size(44.dp, 44.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
                tint = colorPrimary
            )
        }

        Text(
            text = "Cart",
            color = colorPrimary,
            modifier = Modifier.padding(end = 150.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun CartDetails(viewModel: SneakerCartViewModel) {
    var totalPrice by viewModel.priceState
    Column(
        modifier = Modifier
            .fillMaxHeight(1f)
            .clip(RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)).shadow(10.dp)
            .background(white)
            .padding(2.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth().padding(4.dp)
        ) {
            Text(
                text = "Order details",
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 28.sp
            )
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "info",
                tint = colorPrimary
            )
        }

        Spacer(modifier = Modifier.height(28.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Subtotal : $ $totalPrice",
                color = dark_gray,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Taxes and Charges : $40",
                color = dark_gray,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Total :",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = dark_gray
            )
            Text(
                text = "$ ${totalPrice.plus(40)}",
                fontSize = 24.sp,
                color = colorPrimary
            )
            Button(
                modifier = Modifier.wrapContentSize(Alignment.CenterEnd),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorPrimary

                ),
                onClick = {
                }
            ) {
                Text(
                    text = "Checkout",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}
