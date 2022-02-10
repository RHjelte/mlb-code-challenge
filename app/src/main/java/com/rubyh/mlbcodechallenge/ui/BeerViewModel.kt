package com.rubyh.mlbcodechallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubyh.mlbcodechallenge.BeersApplication
import com.rubyh.mlbcodechallenge.model.BeerDetails
import com.rubyh.mlbcodechallenge.repository.BeersRepository
import kotlinx.coroutines.launch

sealed class BeersState() {
    object Loading: BeersState()
    data class LoadComplete(val beerList: List<BeerDetails>) : BeersState()
    data class Error(val message: String) : BeersState()
}

class BeerViewModel(private val repository: BeersRepository) : ViewModel() {

    val _beersState = MutableLiveData<BeersState>()
    val beersState: LiveData<BeersState> = _beersState

    var selectedBeer: BeerDetails? = null

    init {
        loadBeers()
    }

    fun loadBeers(queryString: String? = null) {
        _beersState.value = BeersState.Loading
        viewModelScope.launch {
            repository.fetchBeers(
                getQueryMap(queryString)
            ).let { results ->
                results.fold(
                    { list ->
                        _beersState.value = BeersState.LoadComplete(list)
                    },
                    {
                        println(it)   // Display an error message
                        _beersState.value = BeersState.Error(it.message ?: "Beer list failed to load")
                    }
                )
            }
        }
    }

    fun reload() {
        loadBeers()
    }

    fun getQueryMap(queryString: String?) : Map<String, Any> {
        val queryMap = mutableMapOf<String, Any>()
        queryString?.split(Regex("[,; ]+"))?.forEach { queryParam ->
            queryParam.split("=").takeIf{ it.size > 1 }?.let {
                queryMap.put(it[0], it[1])
            }
        }
        return queryMap.toMap()
    }
}

fun Int.fromStringResource() = BeersApplication.requireContext().getString(this)
fun Int.fromStringResource(vararg formatArgs: Any) = BeersApplication.requireContext().getString(this, *formatArgs)