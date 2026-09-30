package com.mariskal19.brevuni

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

private val denominations = listOf(
    500.0, 200.0, 100.0, 50.0, 20.0, 10.0, 5.0,
    2.0, 1.0, 0.50, 0.20, 0.10, 0.05, 0.02, 0.01
)

private fun money(value: Double): String =
    NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(value)

private data class HistoryEntry(val total: Double, val label: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BrevuniApp() }
    }
}

@Composable
fun BrevuniApp() {
    var selected by remember { mutableStateOf(0) }
    val counts = remember { mutableStateMapOf<Double, Int>() }
    val history = remember { androidx.compose.runtime.mutableStateListOf<HistoryEntry>() }

    MaterialTheme(colorScheme = lightColorScheme()) {
        Scaffold(
            bottomBar = {
                NavigationBar(modifier = Modifier.navigationBarsPadding()) {
                    NavigationBarItem(
                        selected = selected == 0,
                        onClick = { selected = 0 },
                        icon = { Icon(Icons.Default.AttachMoney, null) },
                        label = { Text("Contar") }
                    )
                    NavigationBarItem(
                        selected = selected == 1,
                        onClick = { selected = 1 },
                        icon = { Icon(Icons.Default.History, null) },
                        label = { Text("Historial") }
                    )
                    NavigationBarItem(
                        selected = selected == 2,
                        onClick = { selected = 2 },
                        icon = { Icon(Icons.Default.MoreVert, null) },
                        label = { Text("Más") }
                    )
                }
            }
        ) { padding ->
            when (selected) {
                0 -> CountScreen(
                    modifier = Modifier.padding(padding),
                    counts = counts,
                    onAdd = { value -> counts[value] = (counts[value] ?: 0) + 1 },
                    onSubtract = { value ->
                        val next = ((counts[value] ?: 0) - 1).coerceAtLeast(0)
                        if (next == 0) counts.remove(value) else counts[value] = next
                    },
                    onUndo = {
                        val last = counts.entries.maxByOrNull { it.value }
                        if (last != null) {
                            val next = (last.value - 1).coerceAtLeast(0)
                            if (next == 0) counts.remove(last.key) else counts[last.key] = next
                        }
                    },
                    onSave = {
                        val total = counts.entries.sumOf { it.key * it.value }
                        if (total > 0) history.add(0, HistoryEntry(total, "Conteo"))
                    }
                )
                1 -> HistoryScreen(Modifier.padding(padding), history)
                2 -> MoreScreen(Modifier.padding(padding))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CountScreen(
    modifier: Modifier,
    counts: Map<Double, Int>,
    onAdd: (Double) -> Unit,
    onSubtract: (Double) -> Unit,
    onUndo: () -> Unit,
    onSave: () -> Unit
) {
    val total = counts.entries.sumOf { it.key * it.value }

    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(12.dp))
        Text("Brevuni", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("TOTAL", style = MaterialTheme.typography.labelMedium)
        Text(money(total), fontSize = 38.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.72f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { Text("Billetes", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold) }
            items(denominations.filter { it >= 5.0 }) { value ->
                DenominationRow(value, counts[value] ?: 0, onAdd, onSubtract)
            }
            item { Text("Monedas", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold) }
            items(denominations.filter { it < 5.0 }) { value ->
                DenominationRow(value, counts[value] ?: 0, onAdd, onSubtract)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(onClick = onUndo, modifier = Modifier.fillMaxWidth(0.5f)) { Text("Deshacer") }
            Button(onClick = onSave, modifier = Modifier.fillMaxWidth(0.5f)) { Text("Guardar") }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DenominationRow(
    value: Double,
    count: Int,
    onAdd: (Double) -> Unit,
    onSubtract: (Double) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().combinedClickable(
            onClick = { onAdd(value) },
            onLongClick = { onSubtract(value) }
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(money(value), modifier = Modifier.fillMaxWidth(0.78f), fontWeight = FontWeight.SemiBold)
            Text("× $count", fontSize = 18.sp)
        }
    }
}

@Composable
private fun HistoryScreen(modifier: Modifier, history: List<HistoryEntry>) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Historial", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        if (history.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Todavía no hay conteos.")
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(history) { entry ->
                    Card(Modifier.fillMaxWidth()) {
                        Row(
                            Modifier.fillMaxWidth().padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(Modifier.fillMaxWidth(0.75f)) {
                                Text(entry.label, fontWeight = FontWeight.SemiBold)
                                Text(money(entry.total))
                            }
                            IconButton(onClick = { }) {
                                Icon(Icons.Default.Delete, "Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MoreScreen(modifier: Modifier) {
    val context = LocalContext.current
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Más", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        MoreItem(Icons.Default.Settings, "Configuración") {}
        MoreItem(Icons.Default.Share, "Compartir Brevuni") {
            context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Descubre Brevuni")
            }, "Compartir Brevuni"))
        }
        MoreItem(Icons.Default.Star, "Valorar Brevuni") {}
        MoreItem(Icons.Default.Info, "Acerca de Brevuni") {}
    }
}

@Composable
private fun MoreItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, Modifier.size(24.dp))
            Text(title, Modifier.padding(start = 16.dp), style = MaterialTheme.typography.titleMedium)
        }
    }
}
