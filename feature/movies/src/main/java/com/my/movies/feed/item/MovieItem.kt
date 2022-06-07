package com.my.movies.feed.item

import com.my.core.extensions.load
import com.my.core.presentation.BaseItem
import com.my.movies.R
import com.my.movies.databinding.ItemWithTextBinding
import com.my.movies.domain.Movie

class MovieItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BaseItem<ItemWithTextBinding>(R.layout.item_with_text, ItemWithTextBinding::bind) {

    override fun bind(viewBinding: ItemWithTextBinding, position: Int) {
        viewBinding.description.text = content.title
        viewBinding.movieRating.rating = content.rating
        viewBinding.content.setOnClickListener {
            onClick.invoke(content)
        }
        viewBinding.imagePreview.load(content.posterPath)
    }
}