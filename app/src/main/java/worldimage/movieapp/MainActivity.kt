package worldimage.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import worldimage.movieapp.movieList.domain.model.Movie
import worldimage.movieapp.movieList.presentation.MovieListViewModel
import worldimage.movieapp.ui.theme.MovieAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                val movieListViewModel = hiltViewModel<MovieListViewModel>()
                val movieListState = movieListViewModel.movieListState.collectAsState().value
                if (movieListState.isLoading) {
                    SampleTextView(textLoading = "Loading...")
                }
                if (movieListState.movieList.isNotEmpty()) {
                    SampleTextView(textLoading = "Completed!", movie = movieListState.movieList.first())
                }
            }
        }
    }
}

@Composable
fun SampleTextView(
    modifier: Modifier = Modifier,
    textLoading: String = "",
    movie: Movie? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = textLoading,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic
        )
        if (movie != null){
            Text(
                text = movie.title,
                fontSize = 28.sp,
                fontStyle = FontStyle.Normal
            )
        }
    }
}