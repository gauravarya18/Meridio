package com.example.tinder2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> al;
    //  private ArrayAdapter<String> arrayAdapter;
    private int i,a,count=0;
    private MyAdapter arrayAdapter;

    //private int i;
    private ArrayList<String> a1;
    // = (ArrayList<String>) getIntent().getSerializableExtra("a1");

    private FirebaseAuth mAuth;

    TextView level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

//         level=(TextView)findViewById(R.id.level);
//        level.animate().translationY(-2300).setDuration(2000).setStartDelay(500);






           int x=(int)getIntent().getSerializableExtra("mapid");
        a=x;
        ArrayList<String> list1 = (ArrayList<String>) getIntent().getSerializableExtra("a1");
        ArrayList<String> Titles = (ArrayList<String>) getIntent().getSerializableExtra("Titles");
        ArrayList<String> Urls = (ArrayList<String>) getIntent().getSerializableExtra("Urls");

        Log.d("suthar", Titles.toString());
        //final ArrayList<String> a1 =  (ArrayList<String>)getIntent().getSerializableExtra("FILES_TO_SEND");

        final ArrayList<NEWS> list = new ArrayList<>();

        for (int i = 1; i < Titles.size(); i++) {
            list.add(new NEWS(Titles.get(i),Urls.get(i-1)));
        }

        arrayAdapter = new MyAdapter(this, list);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                list.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                if(count==6) {

                    Intent intent = new Intent(MainActivity.this, ChooseTask.class);
                    intent.putExtra("mapid", a);

                    startActivity(intent);
                    finish();
                }
                 count++;
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                if(count==6) {
                    Intent intent=new Intent(MainActivity.this,ChooseTask.class);
                intent.putExtra("mapid",a);

                startActivity(intent);
                finish(); }
                count++;

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {


                list.add(new NEWS("Well Done !!","data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhUTExAVFhUXFRUYFxUYGBUbFxUYHhUYFxoXFxgYHSggGBolGxoXITEhJSkrLi4uGB8zODMtNygtLisBCgoKDg0OGxAQGTMmHyUtNzc3MjIvLS01MjctMjUrNS4tLS8tMTAtMjc1LS0tNy0vNS0tMDUtNy8tLS03LS0tLf/AABEIANQA7gMBIgACEQEDEQH/xAAbAAACAgMBAAAAAAAAAAAAAAAAAQIGBAUHA//EAEAQAAECBAIJAwMCBAQEBwAAAAECEQADEiExQQQFIjJRYXGBoQZikRNCwbHRI1KC8BSS4fEHcsLSFjNEU2Oisv/EABoBAQADAQEBAAAAAAAAAAAAAAADBAUCBgH/xAAxEQACAQMDAQUHBAMBAAAAAAAAAQIDBBESITFBEyJRYfAFMnGBkcHRFKGx8VJi4UL/2gAMAwEAAhEDEQA/AO1LVVYQIVTYwLTTcQITVc4wBFCabmBSKi4wgQqqxgUqksMIAktVVh1gSqkUnGBaabjpAlNQc4wAkJpuekJSHNQwhoVVY9YSlMaRhAElmqwgSphScf3gWKbiBKXFRxgBIFNzCKXNWX7Q0GqxhFTGnKAJLNWGUAUwpzwgWKcIAlxVnjACQKcc4RRerLGGg1Y5Qiq9OWEANe1hlDqtTnhCXs4Zw6bVZ4wAkbGOcKm9WWMNG1jlCqvTlhADXtYZQ6rU54Ql7OGcOm1WeMAJGzjnCCb1ZYw0bWOUIKvTlhADWKsMoZU4pzwhLNOGcMpYVZ4wAkGnHOEEsassfmJIFWOURCnNOWEANYqwiQnAWiKzThEhJBvAEEIpuYFIquIEKqscIFqKSwwgCS1VWHWBK6QxxgWmm4gQkKDnGAEhNNz0tCUio1DCGhVVjCUpiwwgCS1VWHW8CVsKTjAsU3ECUghzjACQmm58QihzVl+0NBqsYSlMaRhAElmqw8wBTCnPD5gWKcIAlxUcYASBRjnwhFLmrLH4hoNWMIqY05YQA1mvDLjDC7U54QLFOGcASGqzxgBI2Mc+EKi9WWMNG1jlCqL05YQA17eGXGHVanPCEvZwzh0hqs8YASNjHPhCovVljDRtY5Qqr05YQA17eGXGGVWpzwhL2cM4dNqs8YASDRjnwhBDGrLH5iSBVjlEQpzTlhADWK8MuMMqcU54fEJZpwhlLCrOABBosfEQMkm9rxNAqxiBmkWgCalVWHmBK6bHxAtITcYwISFXOMAJCabnpaBSKrjCEhRVY4QKUQWGEASWqqw63gSukUnGBaabpxgSkEOcYASE03PS0JSHNQwjzOkpqShagCskIGBUQHLDNg5j0UogsMIAks12HmAKYU5+LxVPU3qEy1/RkPU9JKbqUr+VPDri9ox9H1NpxH1CpAONJWuv5D37xSned5xpwcsc4LMbbuqUpJZLkgUXPiMbTNLly9tcxKQ4xLHpGk0DW028mcCF5Pi7Pj91n/1y1esJoSslV1XYnIcE8B0xiCp7TjjuLfz6eR3C0bliTN7pfqVB/wDLkzV82CB/9yHHSMVfqSazCTLA90xT/AT+Yq+kay5xgzNPPGKUr2vLqXI2dPwLen1HOGAlD/OYY9RTXdpR/wA4iknTTxgGmmOP1Nb/ACZJ+lp+Bfv/ABGstVJSf+Vf/cIzpPqKUU0qC0WZyHT8peOcy9PPGM/R9Zc4kjfV49c/FEUrOD6HSdGnJaoKCgcCkuInRerLHnFA0XTihaVS8yKkjBQfBo2A07SdMURJDSgWqJKUdLXV56CLtL2kprGluXgipOzcXzt4lvXt4ZcYddqc8OUVI6FpUjarbmlRKf6gf2je6n1gJyCSGmILLHPIjkf3iehdqpPRKLjLwZFUoaVqTyjPRsY58IQRerLHnEVzUhKlzCyUgknAAAOSewhy5tTMQUnAi4IyIOcXCAksV4ZcYZU4pzw5WhL2d2GUhqs8YASDRjnwgCGNWWPO8CBVvQgok0nCAGsV3HmJCcBa9ois07sSEoG8ARSim58QlIquPMCFFVlYQLUQWGEASUqqw63gSumxgWkJunGBKQQ5xgBITRc9LQlh9pwBz5Q0GqysIqeia/Ok6FpTMFpl6QwH8pSooI5hJSDz6xxKpGLSfU7hTct16yafU+s1aTrD66jYA0A/YhRpT0LOTzUY6KlbbJx/eOY+kkD6xfAyz4I/cx0FWsJSJaFzVgFQDDNR5JFz2EZthcatep+Zau6e8dK6FP8AR8sK06bMWHMtJbkpSrn/APQ7xeyhzVl5tFK01J0PTRpJSfoTnC2B2Sbm3UVdHEXNE4FqSCgsQRcEHMGLNmlCLpvlP0yO5zJqfRr0ir+v5C1yxMloV/DBUpQxSAQXcF7XPK8U9fq5SUpTPkpnJJZ91QtjgxPxHV9NkAoUkfclST3BEcD0zR9h3Oy1vHURTvKEVVUn/wCv6LdlJTWl9C1yl6FODpXNlHgoOPz+sEzUSft0pHcN/wBUVvVq2yPaN6ueDx7pUPxGXVzCWEX3FrhjOoFf+/K+THmdTEY6RKHePGYoRjqSeEFKTPve8TYyNWIJb/EBR4JA/VzG70L08LF+5ufgMI0Go0ETCWfZOBHEc4u8grpskJYYkucOAt5iN5zhsgnOSI6Hq8JekFS2IHHA4cI3uoZRRo0qUUUqCACLWVm7ZvGBqqUywq5UHLnKzWGAxjfUhqs8e8bXsuilF1PkZt1UbekjSEghQcKt/fzGh1SinSlNh9FzzIWw8PG00/TghLq3jZI58egjB0MpkgzJxpC6UuQdlIch2wclRfmIsVnGVeH+uW34ZOKeVB+Zn63WFSl/8pTf3Wiof8PdZqBXoii42jLfIpUy097K7K4xadZTUkICVApUFKcFwWAAIPDaMc/9LP8A42QRmuaT3lTD+Ygq3GLuKXkvr6RNSp5oSz8fodQQaMc+EIIY1ZY87xpdA16J2mzNHsUplVBXuCgFX4MpPweMboKL05YRpwmprKKcoOOzGsV4ZcYZU4pzw5WhL2d2GUgCoYx0ciSaLHxCMgm9rxJAq3ogZih/tAE1LqsPMCV02MC0gXTjAhIN1YwAkppueloFIquIEEmysISlEFhhAElKrsOt45PqFRkTTLJa5lnktLoIPIs3YR1PTZolS1TAHpSpRAxIAJYP0ig640aVNnS56Vj6WkBROTTAEuk/ykglTYulRjN9pRbgpLles/wXbN7tPhmMqUdDJUbsg0e4lkgcrkPwxj19KIVN0gzJhKqBLS5/mUWAHAJSCw98eusJqZshSCajKU6VZkCwPVnBjC0TWP8Ah5QSgAzZk0LAOAumWh+pTYdT1xabSZeabi/E6VpklE5BlqS6TiD/AKZxVv8AD6RoBP0yZsjNJuUft1FuMWbQnEqWSqpZQmpTC5ZyWFhePckUlSizAkk2Zs49JUoqqlJPD6P1/BlQqOGY8rwNbqjXUqZgWUbUnjyOB/XlHKfUui0TtIQ2C1kDkTUnwRHQZY0fSFn6ToWQTugIXmXGRa+Aio+rtGUmalSi9aWvi6dkgnNgU3/3jLuK0pRSlh4ezXD/AAy9bRUZ7beTK3q2N++yOg/SNBoIYtwLRvkHZEZ1172TSkY82MRcZc2MRcfIHx8G19NJ2l/0jyYur7Jioel0Yn3DwH/MWxZsBzjh+8ypU5NnqhYTUtVgAz2YZlyegjB1h6kFdEhJmLJtY2PTExpf4mlTlSEzKJUvbmL4BhhxJAx686t16a0rRAr6MgEEu6lAVLa52vLWztjGzbTm6cacXpT69X8F9ypUhFNzay/Dw+Jkap1VMUfqz1VTOBwTwwt2FhGw1rSZSkKDiwPC5AMZS9nd75xXfWmlzJUtC0EEEtMSRi6ksQcQQphwubWi5UpwoW8kvD5tvbLK8JSq1UU3RdLXo80y1KJQFrQRkHsJg4OKSevKM7QNFMpJ0hRpASQniXs44PgOsYunIRPmpWNybSVDMEChaTzZI+QY2esJ0udUlSwlMsCkOACbuq/DAd4863vnqar3PL0Fo6l6VMmcJaquRUpFI+Ekdo6HW4pzw5WipelpiNHTJlEkz9JBWoAXQkSypIUDdNssXWYtpSGfP89I9JZQ0UUupl3Us1MiSaMc+EAQxqyx53hoFW9+0RBJLHCLRXGoV3HmJCeBZjaIrNO7+8SEtOf6wBFKKbmBSKriEgk2Vh8QLJBZOEASUqqw6wJXTYwLAF04/MCUghzjAEFSQAQq4IKSOscs0KYrRZq5K7pCilTgHoopLgpUCFNlU8dVQSd7D4ileuZMv66EtStSNlf2zGJdCuBDghXNi1jFD2hT1U9Se6LdpPEnF8M1czVCpaFTJRrlqBLJuUgnK+0AO9hzMa3U6fqaRKVkqZUOSQhVA7AJHaM3VumrkKYYPdGXUcDzEQ0pSETfq0vLaop4ElIdutv6o8+pc+Jp4fB0nVq2QFZbQ+FEfiJ6VowmpUDurSpJ4sRSYpqddrTPkaKkhKErqmtkC6/pg8AFJfq2V7sokFhh/b3j01tNShpfKSz9DHqwcJZ8SravkzpGx9BKikmmYEkkg8SD+sR11qIzNGU4JmpFSBmTioFs1B7cWi2rDbv7wABnO9/bWiKFjFbNtrotts7fM6dzLOpLc4WlO0/G8bWSdmNj6w1IZM2tKf4cwkjglWKkfqRycZRq9HMYd1TlB6Zco24VFUipIjNjEXGXNjEUIjhwdPgsvpiWyAep8t+giwyUVzAkd+QxJ+PxGp1akS5Y5D9BFp1FohSKlC6+OSfwTie3CJbOg69XHTllC4noTZW16FP0TSZq5UhU6TNDMkEsMQkpF7XHAg/GdqLVq1z0zlyBIShNMuWAxdil1DHBRxvhFrXbd75w6Qz/AHfnpG8rSKknnZPKXrcou4enGN3tkSdjHPhFe9XoqlTU/wDwqUOW9/2iM/1BPUnRpqgohYTsHMK484qM/XQnyUzVoClGmSUjJbKUCH+1QL8iCLtEV/U7jgudn+53a03qUzR6vJ+pQlKipRCkgZEWUTwcFN/YI2x0QaK8yYoKmKDJAAJHGl88NqzNbEv56s0j6EslgZqgz8AP9Yw1pMwqWtbDFS1ZDkM+g8C8YMZZfd59fQ02n14Nz6FkqXpK9IXelJH9SsgTiyan/wCcGL6EMassfmNT6SQg6LLKZZQk1EBR2jtEVqIa6gAeTsLCNqCXb7fx1j01rS7OkkY9xPXUbGoV4ZcYZW4pzw+IS7bv7wyAzje/t7RYIQSaLHxEfoE3cXiSA+9+0QK1ZfpAE1LqsIErpsYFgDdx+YEAG6sfiAElNFz0gKKrwkEnew+IFEgsnCAGpVdh1jQ+sNT/AF5DJDzJZqT7gd5A5mxHNIjfrAG7j8wJAIdWMcTgpxcX1OoScZKSOWav08DZnIrGFTbY5EZ/rG01joCZssqkKSUlKQpDNgoKcZhVsDj1jA1pM/jLl6QAmaFMJwASJoxSqYkBqiGuG7s0eui6BOSQpGPIi474iPK1Y9nNxNpPKUjW6mWVaShasVzF1dVVH9WEdR0DS0lIQTtXDOHPAtjg0cxkK/jmzFM5iMqgu5HUgn5jJ1tpq5JUjBc1X1ETAS4SZimHJQCSAXwbjFq2uZU6jljPj+SKvRVRJZOlaNOS1SVBQdSXTg6SUkdiCO0ehQ+13+IrP/D+cDoyZashUO52h8v8xZSS7Dd/t7xvUaqqwUkZlWGibieWnaMjSEGWtLpOIPggjAg3eOa691QdEmBClBSVVFCsyAzhQyIccj4HUFht3941uvNTI0mWKjTMSSUq4HAgjMENbpEF5bKtDZd4ltq7pyw+Dl02DV8mqYHwHk5ADMx6aboxQpSVBiFKB6hRBixejtV1lybAgkgccA/YmPPRpyk+zjyzZqVFGGp8G81JqYqZcywDFKP0KufKLFX9ueEJQpajxeGwZ/u/PSPTW9CNGGmJg1KjqSyxJ2Mc48vqprIrFQSFlL3CSSAejg/EeqL73bKOcesNYFOlpmJLAII5KlhSTSeIO0W5x8uLhUUvM6o0e0eC0+p9LCpSykghEtRDEEFRB4dvkxz/AFVIWViWgA3lrLuzoJAdsmWqNuucVylzWpRNCaUu+5MIUTzw7N2xdUJWozUyxcUVF2LGq3LDzHn61eU6jk1ualKmoQwbbSvoSUgLImTLuwDkniMEjkTGnkaNM0uamWkUpdyE4IS91nieA4t1iekaFReYoJTne55DiYsXoWYpallKPp6ONlmBVMXY1LWzukWYMNvDh1Z0lVqJPg+VZunByRapMhNKUIFKUJCQOAAYD4EetbinPD4hLtu984ZAZxvfnO0emMYSTRjnAENtZY/MCL73bKECXY7v9teAGoV3EMTwLNhCXbd/eJBCc8esARCKbmAoqvCQSd7D4gUSDs4fMASKq7C2cAXTaBYA3ceV4EgEOrGAElNFzfKAoq2oEEnew52vCUSCwwgDT+o9Rp0sApZM1I2VHAj+RTZPnl3INQ0HQZskzEqmKlLl3odJSbOCxcEG4cfqDHSVgDdx5XtFB11p2k6PMAnoROS5+nOVLl1kcMGq4i3GMv2hbwa7Th+uS9aVZe4VpOmEzlTAxKi5DMlQOPly4wfnG/0nQhpiUzETNuWkJoU2AdknMYljcRjTNJ0ebvy6FfzJly0kHnQ1XeMAz1StuWq6SGNw4cO44NGO5b4i+S/jJvtQ6YZMqkp20zqQk23gVF/hfxGdK9UK/wAamUpky0y1JWAXFailQX2BHR1xr9XaVLnrqWGSSj6gvZSXIsLg4fvFc+iuTNJVUSmYSFEEVgBiQ+RD/IiejXqU45jLbPHn/JFKlCcnqW+DsSdm+L8IKH2u/wARrNRaQFJSKnSU1I5cv9OsbIkuw3fDZ3j0FCsqsFNGVODhLDOdetpYGkKI+5lfKEj9QfmLP6JSBo5tdSm7BIH6vFf9fN9e2SEf9R/IizekG/wo4gq+XcfiMq3iv1svn6/c0K7f6WPyNynYxu8YusdKEpBmkj2jich/eTxlIL73m0Uv1lpjoZ7rcJH8qMz1byeUX7y57GG3L4/JSoUu0lh8C1V6nmTNEBWKjLBTMW+0QwVUzY0EdSDhGu1poCtJ0laU2CQlJUcBmepcm3KMP07oLmaqY4TRsAhVKl2INgxAYg9RHpp2tlJeXLstayVnMAnC2Bbv+cOtUnNxcnn7GpGnFN6Ue2s9ITJlp0eWqsoe5ZkEkkm2KnJtlGD6cnGtUtMyiu5UwqID4FWZUTfnBo0uW/8AEJCR9qQXVyfIRmHXEqWCJchDMxeVJAI9yiKj5iKMlL3n9ztrCwkeur/TczSZqts/TSpjNUXODlKeJ8DqGi/6Ho6JUtMmWlkpDD9STxJLknnGj9KnSlD6kxpcspaXJShIDO9arOOQtiS2EWMgM/3fnpHoLOhGnDK5fiZdzVlKWG9kJJoxu8AQ213+YEX3uz2hAl2O74bK8XCsMivCzQyt9nt8Ql23e7XhkBnG9/b2gBJNFjd4X0CbvjEkX3vNogVqydukATK6rYQBdNoFgDdx5XgQAd7HnaAEE0XxygKKrwkEnew52gUSDs4QAyqu2GcMLp2YFgDdx5XtAkAjaxgBBNF8co89I0VM1JC0hSTilQcFo9EEnew52hKJdhh45wBW53o2Ss/w1rl8jtJ7PfzFeVqsIVMStRKkTKQiwK01MAAxLqSxGOMdHWw3ceV4pOvJesJaqq/rIuykoAmpHBQQyh1TbO2EZV5Z01HVGO/ks/tkvW9ebeG/r/RWJkhcuYSmVOlDFNYUFBPBRIDh+OPWNzomunTROlhaTmBfuMO4aMBGuZtxsA52JPeo/iHomjTJqthBUTjSkAD4ACR8Rizlql3V8uS/hY7xZPTzfVTLQp0OpSTwBSpwf6v1i3Vts9vmKxofpZpYKpq0zXcKlKIoth7ucZustKOi6IpUyb9SaHSlRASSok0ukcBfmEmN6xjOhRfaLHX/AIZtfTUmtD34KT6m0iudMILj6igOiQEfqCYtPoea8tSXwKV9XDH9B8xRNIDBI4C/U3MWT0hpVCkF7EUq6Ox/B7Rk0a+m4VR9Xv8AM0K9LNHSun2LvpxK5a6cQhTczSWikHSZdX1ZgrLAIRiwGfDFzFq1tqpU1QbSZiJTAGXLYFRc3K8WIs3KK9rr02tG1KFaf5cVp7YqHn9Yv+06VWeJxjsvWcFO1lBbN7s02stbzZthsg2ZL1HlVj8NHhqvQHcrkzUhwBNKVCWgOAoklLFRdrm3cwS9JXKJZgcwpIfpcOPmPaRrTSFKaUl1GxoC7j3AHDraMqk03hrOfXBeksLY2mqvTYnrmATSEIpuwU5NTpcMHACT/VFg1d6Z0eUrdK1jBS2IB4hIt3Z4j6d0fS0iqfMTcMmUhKNnmpSRjjYFuuW9YM/3eX6Rv2tnShBPTv5/joZlevNyaT28hDYxu8FH3d2gRfe7PaECXb7fDdYvFUZFfJoK32ez9IF23e7XhkBnG95fO0AIGjm8FDbXdusCL73Z7QgS7Hd8NleAGRXfCH9drNhCXbd8XiQSnNn6wBEIpvjAUVXwhIJO9hzgWS+zhygBlVdsM4YXTswLYbuPK8CWbax5wAgmi+OUFFW1CQSd7Dna8CiX2cOWEAMqrthnAF07MNbDdx5XtAkBr4+eUAIJovjlBQ+146QIL72HO0Ikvbd8c7wBGZLTMxSLcQDE0kJFDcuV4a7bvi8AAa+955WgBAUc3iievJil6TKQ4oCKwl7klRSSeO63zxixa29SyJDha61jCWm6n92Se8c903TpmkTjPUQCzBLbKUh2Azzx5xm+0K8dGhPf1yX7KjJy1tbHnOlqJJpV8GNnqFCwLpUGVmCAx5nvGmJI5dCYzdSzdpQzLFzyjBku6aklsdQ1etpYJu9vi0ZFH3d2iu6n1mU7MzaTl7enKN/LmhW6XT+OfCPSWVxCpTST3SMKtTcJMcyWJmKRbiAYaSAKAGythDXbd7teGwZ/u8v0i4QiGxzeCj7u7QIvvdntCcu32+G6wAzt8mgr+3s8C7bvdrw7M/3eX6QAgaObwUNtd26wIvvdntCBLsd3w2V4AZFfJoK32ez9IF23e7XhlmtveXztACBoti8H0Hu+MNF97zaIFSsnbpAEiuq2EAXTbGGtvtx5QIb7secAIIovjlBRVtYQkP8AdhzgUS+zhygBlVdsM4AunZhrb7ceXCBLNtY88YAQTRfHKCira8dIEP8Adhz4wlEvbd8QAyqu2GcFbbPnrDW3248oEs197zygBAUXxeCh9rx0gR7vMIkvbd8c4A49rWcJmkTlFw82Yx5VlgRyETlSSbBj0P4x8Rd/UPo2XOWZslf01kuoM6FHiQLpJ4j4e8V1fpbSkG8oKtihST4UyvEeeuLOtFtpZ+G5tULim4pZx8TVTdHX/Ir/ACmDQpExKwr6a2zNJAbqYy5uqNJH/p5vZCvwI8R6c0teGjL6qDeVNFWNGo1jS/oyd1I495Fp0ZPEjtf9I3+qF4jiB/fmNXoGpprCoJTxvUR2TbzFg0TRUoSw3sycScv9ovWFnVjUU5LCRmXFWDWE8nsNjm8FH3d2gR7uzwnL+3w0bpRGdvk0Ff29ngX7e7Q7N7vLwAhsc3go+7u0CPd2eEHf2+GgBkV8mgrfZ7P0gX7e7QyzW3vL5wAgaObwUNtd26wI93Z4QJe+74bKAGRXfBof12s2EJft8RIBObPAEaKb4wUVXwhIf7sOcC3fZw5QA6q7YZwV07OMNbfbjygSzbWPOAEE0Xxygoq2oEP92HPjCU77OHLCAHVXbDOCunZ89Ya2+3HlAlmvj55QAgmi+OUFD7XjpAj3Yc4Rd7bvjnADJrtg0FbbPZ+sNft8QBmvveeUAJqObwUPtd26QI93mEXe274bOAG9fJoK22ezw1+3u0AZvd5eAENjm8FH3d2gR7uzwrv7fDQA9/k0Ff29ngX7e7Q7N7vLwAtzm8FH3d2gR7uzwrv7fDQAzt8mgr+3s8C/b3aHZvd5eAE9HN4KG2u7dYEe7s8IO993w2UANq+TQVvs9n6QL9vdoZZrb3l84AT0WxeD6D3fGGj3eYgSrJ2gCVddsIK6bYw1t9uPKBDfdjzgBUUXxygoq2sISH+7DnAp32cOWEAOqu2GcFdOzjzhrb7ceXCBLNtY88YAVNF8coKKtrx0gQ/3Yc+MJTvbDxADqrthnBW2z56w1t9uPKBLNfe88oATUXxeCh9ru3SBHuw5wi723fHOAG9fJoK22ez9Ya/b4gDNfe8vlACajm8FD7XdoEe7s8Iu/t8NAD3+TQV/b2eBft7tDs3u8vAC3ObwUfd3aBHu7PCu/t8NAD3+TQV/b2eBft7tDs3u8vAC3ObwUfd3aBHu7PCDv7fDQA2r5NBW+z2fpAv292hlmtveXzgBPRzeChtru3WBHu7PCDvfd8NlADau+DQfXazYQL9viJCnNngDz0bHtBpG9BBAHppOHeHI3fmCCAPLRce37QTt74gggD00rDvDk7vzBBAHnouPaFN3+4/EEEAT0rAdYlL3OxgggDz0XEwl7/cfiCCAJ6VlEk7nYwQQBDRc+0RO/wB4IIAlpWXeJ/Z2gggCGi59oiN/vBBAEtKy7xNW52EEEAR0XOII3+5gggB6ViI9Jm52EEEAR0XAx4LxPUw4IA//2Q=="));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");

//                Intent intent=new Intent(MainActivity.this,NewsActivity.class);
//                intent.putExtra("mapid",a);
//                intent.putExtra("level",2);
//                startActivity(intent);

            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }





    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(MainActivity.this, ChooseTask.class);
        intent.putExtra("mapid",a);
        startActivity(intent);
    }
    public void level2()
    {

    }
}