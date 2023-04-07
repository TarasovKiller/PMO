# Выписка из книги "Программирование для Android" Колесниченко c 191-197

## 9.3. Сохранение и восстановление
состояния деятельности

Перед завершением работы деятельности вызывается функция
**onSaveinstanceState ()**, сохраняющая состояние деятельности. При повторном создании
деятельности вызывается функция **onRestoreinstanceState ()**. Эта функция
используется для восстановления состояния деятельности.

```java
String my-'data = "Му data";
float[] my_array = {1.0, 1.2, 1.3};
// Сохранение состояния деятельности
@Override
protected void onSaveinstanceState(Bundle outState) {
super.onSaveinstanceState(outState);
// Сохраняем связанную информацию
outState.putString("data", my_data);
outState.putFloatArray ("array", my_array);
}

// Восстановление состояния деятельности
@Override
puЬlic void onRestoreinstanceState(Bundle savedinstanceState) {
super.onRestoreinstanceState(savedinstanceState);
// Восстанавливаем информацию
my_data = savedinstanceState.getString("data");
my_array = savedinstanceState.getFloatArray("array");
}
```

## 9.4. Передача данных между деятельностями

Существует несколько способов передачи данных между деятельностями:

1. Использование публичных членов класса

```java
public int flag;
		...
MainActivity.flag = О;
```

1. Использование предпочтений
2. Использование базы данных
3. Использование экстра данных
    
    Представим, что нам нужно передать вызываемой деятельности какие-либо данные.
    Для этого используется метод **putExtra ()**:
    
    ```java
    int flag = О;
    int coins = 100;
    ...
    Intent startButton = new Intent(this, StartButton.class);
    startGame.putExtra ("flag", flag);
    st_artGame.putExtra ("coins", coins);
    startActivity(startButton);
    ```
    
    Первый параметр метода **putExtra ()** - это название экстра-переменной, по которому
    можно обратиться к ней с целью получения ее значения. Второй параметр -
    это переменная, значение которой передается.
    Для получения значения экстра-переменной в коде класса startButton, содержащегося
    в файле **StartButton.java**, вызываются методы **getintExtra ()** или **getStringExtra ()** из класса **Intent**, который удобно использовать для получения строковых переменных:
    
    ```java
    // Функция getintent() возвращает намерение, запустившее эту деятельность
    Intent i = getintent();
    // Получаем экстра-переменную с именем flag,
    // если таковая не найдена, возвращаем значение по умолчанию - О
    flag = i.getintExtra("flag", О);
    // Получаем экстра-переменную с именем coins, значение по
    // умолчанию не задано
    coins = i.getStringExtra("coins");
    ```
    
    ## 10.1.1. Запуск потока
    
    Потоки позволяют выполнять несколько задач одновременно, что дает возможность более эффективно использовать системные ресурсы. 
    
    Создать поток:
    
    ```java
    Thread myТhread = new Thread(
    // здесь будет описание объекта RunnaЫe
    ) ;
    ```
    
    Описать объект Runnable в конструкторе потока:
    
    ```java
    new RunnaЬle() {
    	public void run () {
    		play();
    	}
    }
    ```
    
    Запуск потока
    
    ```java
    myТhread.start();
    ```
    
    Полный код
    
    ```java
    Thread myТhread = new Thread(
    	new RunnaЬle() {
    		public void run() {
    			play();
    			}
    		}
    );
    myТhread.start();
    ```
    
    ## 10.1.2. Установка приоритета потока
    
    Дпя установки приоритета процесса используется метод setPriori ty (), который
    нужно вызвать до метода start () . Значение приоритета может лежать в диапазоне
    ОТ **Thread.MIN_PRIORITY (1)** ДО **Thread.МAX_PRIORITY (10)**:
    
    ```java
    myТhread.setPriority(l0);
    myТhread.start();
    ```
    
    ## 10.1.3. Отмена выполнения потока
    
    Правильный способ завершать поток myТhread (измените идентификаторы на идентификаторы
    вашего потока):
    
    ```java
    if(myТhread != null) {
    	Thread durrmy = myТhread;
    	myТhread = null;
    	durrmy.interrupt();
    }
    ```
    
    Также есть способ объявить все запущенные потоки демонами:
    
    ```java
    myТhread.setDaemon(true);
    myТhread.start();
    ```
    
    ## 10.1.4. Обработчики RunnаЬ/е-объектов: класс Handler
    
    При сложных вычислениях может понадобиться очередь RunnаЫе-объектов. Помещая
    такой объект в очередь, вы можете задать время его запуска, - например,
    спустя какое-то количество миллисекунд после помещения в очередь, или же указать
    точное время запуска объекта.
    Для демонстрации обработчика потока мы разработаем программу, запускающую
    фоновый процесс, который будет каждые 200 мс получать текущее время и обновлять
    текстовую надпись в главной деятельности приложения. Кроме того, мы организуем
    кнопку Start, которую вы сможете нажимать. По нажатmо кнопки
    Start будет выполняться какое-либо действие, в нашем случае - просто изменение
    надписи на кнопке. Так, после первого нажатия надпись "Старт" заменяется числом - количеством нажатий кнопки. В то же время в фоновом режиме наш поток
    будет обновлять текстовую надпись.
    
    Создайте новый проект. Код разметки приложения приведен ниже:
    
    ```xml
    <?xml version="l.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	android:orientation="vertical"
    	android:layout_width="fill_parent"
    	android:layout_height="fill_parent"
    	>
    	<TextView android:id="@+id/text"
    		android:layout_width="fill_parent"
    		"'
    		Глава 10. Потоки, службы и широковещательные приемники
    		android:layout_height="wrap_content"
    		android:text=""
    		/>
    	<Button
    		android:layout_width="wrap content"
    		android:layout_height="wrap_content"
    		android: text="Orapr"
    		android:id="@+id/start"
    		android:layout_gravity="center_horizontal" />
    </LinearLayout>
    ```
    
    Код приложения:
    
    ```java
    package com.example.mytimer;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.os.Handler;
    import android.os.SystemClock;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    public class MainActivity extends AppCompatActivity {
        private int buttonPressCount = 0;
        TextView buttonLabel;
        // Счетчик нажатий кнопки
        private long sTime = 0L; / / Счетчик времени
        private TextView TimeLabel;
        // Обработчик потока: обновляет сведения о времени
        private Handler Handlerl = new Handler();
        @Override
        protected void onCreate(Bundle savedinstanceState) {
            super.onCreate(savedinstanceState);
            setContentView(R.layout.activity_main);
            if (sTime == 0L) {
                sTime = SysternClock.uptimeMillis();
                Handlerl.rernoveCallbacks(TirneUpdater);
                // Добавляем RunnаЫе-объект TimerUpdater в очередь
                // сообщений, объект будет запущен после задержки в 150 мс.
                Handlerl.postDelayed(TimeUpdater, 150);
            }
            TimeLabel = (TextView) findViewByid(R.id.text);
            buttonLaЬel = (TextView) findViewByid(R.id.start);
            Button startButton = (Button) findViewByid (R. id. start);
            startвutton.setonClickListaner(new View.OnClickListaner() {
                public void onClick (View view) {
                buttonnLabel.setТext(++buttonPressCount);
                }
            });
        }
    		private Runnable TimeUpdater = new Runnable {
    			public void run() {
    				final long start = sTime;
    				lonq millis = SystemClock.uptimeMillis() - start;
    				int seconds = (int) (millies/ 1000);
    				int min = second / 60;
    				seconds = second % 60;
    				TimeLabel.setText("" + min + ":" + String.format("%02d",seconds));
    				Handler1.postDelayed(this, 300);
    			}
    	};
    		@Override
    		protected void onPause() {
    			// Удаляем RunnаЬlе-объект
    			Handlerl.removeCallbacks(TimeUpdater);
    			super.onPause();
    		}
    		@Override
    		protected void onResume() {
    			super.onResume();
    			// Добавляем RunлаЫе-объект
    			Handlerl.postDelayed(TiroeUpdater, 150);
    		}
    }
    
    	
    ```
    

Кроме метода postDelayed () вы можете использовать метод postAtTime():

```java
postAtTime(Runnable, long uptimeMillis)
```

В этом случае объект r добавляется в очередь сообщений, запуск объекта производится
во время, заданное вторым параметром (в миллисекундах).
Самый простой способ помещения объекта в очередь - метод post (), когда указывается
только помещаемый объект, но не указывается время выполнения объекта:

```java
post(Runnable r)
```