Simple calculator with exp4j
to bind UI components in layouts to data sources used DataBinding  
```
mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
```
the exp4j library is used to calculate expressions, and the expression itself is passed through 
```
mainBinding.input.text.toString())
``` 
in the CalculateAction method


The getCalculateResult method outputs the result in the desired format


Here some screenshots:

![Снимок экрана от 2022-01-17 02-31-25](https://user-images.githubusercontent.com/66710649/149683185-7fca6ba4-8a1e-427b-884a-999085202aa8.png)


![Снимок экрана от 2022-01-17 02-43-50](https://user-images.githubusercontent.com/66710649/149683189-097622e5-d940-4498-a586-46ea569a059d.png)
