/**
 * Created by Павел on 07.04.2016.
 */
var audioCtx = new AudioContext();
var analyser = audioCtx.createAnalyser();
var startOffset = 0;
var startTime = 0;

var smp =null;
var isPlaying = false;
var gainNode = null;
var currentURL = null;

var canvas = null;
var canvas2 = null;
var ctx = null;
var ctx2 = null;
var freqByteData = null;
var volume = null;
var currentVolume = 100;

function playFunc(url,name) {

    startTime = audioCtx.currentTime;
    volume = document.getElementById('volume');

    if (currentURL!= null && currentURL != url)
    {
        currentURL = url;
        stopFunc();
    }

    currentURL = url;

    if (!isPlaying) {
        isPlaying = true;
        var request = new XMLHttpRequest();
        gainNode = audioCtx.createGain ? audioCtx.createGain() : audioCtx.createGainNode();
        changeVolume(volume);
        request.open("GET", url, true);
        request.responseType = "arraybuffer";

        request.onload = function () {
            var audioData = request.response;
            audioCtx.decodeAudioData(audioData,
                function (buffer) {
                    smp = audioCtx.createBufferSource();
                    smp.buffer = buffer;
                    smp.connect(gainNode);
                    gainNode.connect(analyser);
                    analyser.connect(audioCtx.destination);
                    changeVolume(volume);
                    vusiualize();
                    smp.start(0, startOffset % buffer.duration);
                    $('#runString').html("<marquee>"+name+"</marquee>");
                },
                function (e) {
                    alert("Error with decoding audio data" + e.err);
                }
            );
        };
        request.send();
    }
}

function stopFunc() {   //Остановка плеера

if(smp != null) {
    smp.stop();
    /* smp.onended = function () {
     smp = null;
     };*/
    $('#runString').html("<marquee></marquee>");
    vusiualizeForStop();
    startOffset = 0;
    audioCtx.currentTime = 0;
    isPlaying = false;
}
}

function pauseFunc() {
if(smp != null) {
    smp.stop();
    startOffset += audioCtx.currentTime - startTime;
    isPlaying = false;
}
}

function changeVolume(element) {

    var fraction = parseInt(currentVolume) / parseInt(element.max);
    gainNode.gain.value = fraction * fraction;
    currentVolume = element.value;
}

function vusiualize() {
    canvas = document.getElementById('fft');
    ctx = canvas.getContext('2d');
    canvas.width = document.body.clientWidth / 1.4;

    canvas2 = document.getElementById('fft2');
    ctx2 = canvas2.getContext('2d');
    canvas2.width = canvas.width;

    const CANVAS_HEIGHT = canvas.height;
    const CANVAS_WIDTH = canvas.width;
    function rafCallback(time) {

        window.requestAnimationFrame(rafCallback, canvas);
        freqByteData = new Uint8Array(analyser.frequencyBinCount);
        analyser.getByteFrequencyData(freqByteData);

        var SPACER_WIDTH = 10;
        var BAR_WIDTH = 5;
        var OFFSET = 100;
        var CUTOFF = 23;
        var numBars = Math.round(CANVAS_WIDTH / SPACER_WIDTH);

        ctx.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        ctx.fillStyle = '#F6D565';
        ctx.lineCap = 'round';

        ctx2.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        ctx2.fillStyle = '#3A5E8C';
        ctx2.lineCap = 'round';
        for (var i = 0; i < numBars; ++i) {
            var magnitude = freqByteData[i + OFFSET];
            ctx.fillRect(i * SPACER_WIDTH, CANVAS_HEIGHT, BAR_WIDTH, -magnitude);
            ctx2.fillRect(i * SPACER_WIDTH, CANVAS_HEIGHT, BAR_WIDTH, -magnitude);
        }
    }
    rafCallback();

}

function vusiualizeForStop(time) {

    function rafCallback(time) {

        window.requestAnimationFrame(rafCallback, canvas);
        freqByteData = new Uint8Array(analyser.frequencyBinCount);
        analyser.getByteFrequencyData(freqByteData);
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx2.clearRect(0, 0, canvas.width, canvas.height);

    }
    rafCallback();

}