# Product list output application using sqlite database

## There are 3 xml layouts written for the program.


For entering new products:

activity_main.xml

For updating added :

dialog_update.xml

To display added products:

items_row.xml

## class MainActivity

The AddRecord(),updateRecordDialog(), deleteRecordAlertDialog(), setUpListOfDataIntoRecyclerView()  are defined in the MainActivity class, which performs the basic database actions, CREATE. DELETE. UPDATE, READ.
```
fun addRecord(view: View){

        var name = etName.text.toString()
        var type = etType.text.toString()
        var price = etPrice.text.toString().toInt()
```
## class ItemAdapter
adapter make it easy to link data to the control
The purpose of an adapter is to provide child views for a container. The adapter takes the data and metadata of a particular container and builds each child view
```
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_row, parent, false)
        return ViewHolder(itemView)
    }
    
    override fun getItemCount(): Int {

        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvName = itemView.tvName
        val tvType = itemView.tvType
        val tvPrice = itemView.tvPrice
        val ivEdit = itemView.ivEdit
        val ivDelete = itemView.ivDelete
        val llMain = itemView.llMain

    }

```

## class EmpModelClass
Stores models for the database
```
data class EmpModelClass(
    val id: Int,
    val tvName: String,
    val tvType: String,
    val tvPrice: Int,
    )
```

## DatabaseHandler
A class designed for communicating with a database and performing operations on it

For example, the method of creating a new recor:
```
fun addEmployee(emp : EmpModelClass): Long{

        val db = this.writableDatabase
        val contentValues = ContentValues()


        contentValues.put("name" , emp.tvName.toString())
        contentValues.put("type", emp.tvType.toString())
        contentValues.put("price", emp.tvPrice.toString().toInt())
        // Inserting Row
        val success = db.insert("EmployeeTable", null, contentValues)
        db.close()

        return  success
    }
```
Here are some screenshots:

![8ecd1f61-1415-4619-86c1-a1064617f667](https://user-images.githubusercontent.com/66710649/149754408-9551dbde-d3f7-43e9-bee4-17ce8f3092d8.jpeg)


![bdb8382b-3c2e-46d7-aa3d-8677f37b522d](https://user-images.githubusercontent.com/66710649/149754417-eafaf7f9-f63a-4f61-9284-aebfc62721d4.jpeg)


![f4cd70a7-3dce-408f-97a2-1b5129eb00f6](https://user-images.githubusercontent.com/66710649/149754419-f553a7c8-8bbc-4e15-8d0f-08173c9bd7bc.jpeg)
