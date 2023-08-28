package com.iexceed.sneaker.presentation.ui.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.iexceed.sneaker.presentation.Screen
import com.iexceed.sneaker.presentation.ui.list.SneakerListViewModel
import com.iexceed.sneaker.ui.theme.colorPrimary
import com.iexceed.sneaker.ui.theme.colorprimarywhite
import com.iexceed.sneaker.ui.theme.ghost_white
import com.iexceed.sneaker.ui.theme.white
import java.util.*

@Composable
fun SneakerListScreen(
    navHostController: NavHostController,
    viewModel: SneakerListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    /*  Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {*/
    var visible by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize().background(color = ghost_white)) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = "SNEAKERSHIP",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp,
                color = colorPrimary
            )

            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(white)
            ) {
                IconButton(onClick = { visible = true }) {
                    Icon(
                        modifier = Modifier.size(20.dp, 20.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = colorPrimary
                    )
                }
            }
        }
        if (visible) {
            Column {
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(5.dp, 5.dp, 5.dp, 5.dp),
                    value = viewModel.textState.value,
                    onValueChange = { value ->
                        viewModel.textState.value = value
                        viewModel._state.value.Sneakers = viewModel.state.value.Sneakers.filter {
                            it.name.trim().toLowerCase(
                                Locale.US
                            ).contains(value.text.trim().toLowerCase(Locale.US))
                        }
                        // visible = false
                        // viewModel.state.value.Sneakers = viewModel.state.value.Sneakers.filter { it.name.contains(searchedText) }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorprimarywhite,
                        cursorColor = Color.Black,
                        disabledLabelColor = colorprimarywhite,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),

                    shape = RoundedCornerShape(40.dp),
                    singleLine = true,

                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { visible = false }
                    )

                )
            }
        }

        state.Sneakers.forEach { item ->

            SneakerListItem(Sneakers = state.Sneakers, {
                navHostController.navigate(Screen.SneakerDetailScreen.route + "/${item.id}")
            }, viewModel)
        }

        if (state.error.isNotBlank()) {
            Text(
                text = "500 Unexpected error",
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}
/*Search text
*
*
*
*     /*    Box(modifier = Modifier.fillMaxWidth(1f).height(60.dp)) {
                TextField(
                    value = viewModel.textState.value,
                    onValueChange = { value ->
                        viewModel.textState.value = value
                        viewModel._state.value.Sneakers = state.Sneakers.filter { it.name.contains(value.text) }
                        // viewModel.state.value.Sneakers = viewModel.state.value.Sneakers.filter { it.name.contains(searchedText) }
                    },
                    keyboardOptions = KeyboardOptions.Default
                )
            }*/


            */
