/**
 * Created by Павел on 03.04.2016.
 */
//var audioCtx = new AudioContext();             //Подключаем контекст для создания WEB audio API
//var smp = null;
//var isPlaying = false;

var stop = function(){                  // Функция останавливающая проигрывание песни
    smp.stop(0);
    smp.onended();
    isPlaying = false;
};


function playWithFilters(url) {                        //Кликабельная функция. Заставляет проигрывать песню с путем до музыки
    
        filterNode = audioCtx.createBiquadFilter();         // Создаем  узел для фильтров
        filterNode.type = $('.radio1:checked').val() * 2;
        filterNode.frequency.value = $('.range1').val();       //Частотный фильтр
        filterNode.Q.value = $('.range2').val();              // Добротность
        filterNode.gain.value = $('.range3').val();              // Усиление

        var request = new XMLHttpRequest();                       // Создаем переменную для чтения музыки в буфер, а потом из него
        request.open("GET", url, true);                          //Скачивание песни
        request.responseType = "arraybuffer";                      //храним данные в двочином буфере

        request.onload = function () {              //В этой функции подключаем по порядку
            var audioData = request.response;         // Сначала наш источник к фильтру
            audioCtx.decodeAudioData(audioData,        // Потом фильтр к "колонкам"
                function (buffer) {
                    smp = audioCtx.createBufferSource();
                    smp.buffer = buffer;
                    smp.connect(filterNode);                   // источник -> фильтр
                    filterNode.connect(audioCtx.destination);   // фильтр -> "колонки"
                    initFilterGraph();                           // инициализируем подключаемые фильтры
                    $('.radio1').on('change', function (e) {                //переключатель фильтров
                        changeFilterType($('.radio1:checked').val() * 1);
                    });
                    smp.start(0);                              // запустили проигрывание песни
                },
                function (e) {
                    alert("Error with decoding audio data" + e.err);
                }
            );
        };
        request.send();


}