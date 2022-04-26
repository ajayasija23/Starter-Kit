# Starter-Kit
```
fun Spinner.items(list: Array<String>, context: Context){
    val adapter=ArrayAdapter<String>(context, R.layout.spinner_item_with_drawable,list)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    this.adapter=adapter
}
```
