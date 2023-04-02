var medias = new Map();

function media_set_source(media, src) {
  media.src = src;
  var table = document.getElementById(media.id + "s2");
  table.style.display = 'none';  //hide initial controls
  media.setAttribute('controls', '');  //show native controls
  media.play();
}

function media_set_live_source(media, codecs) {
  console.log("media_init:id=" + media.id);
  var ctx = {};
  ctx.media = media;
  ctx.mediaSource = null;
  ctx.streamBuffer = null;
  ctx.codecs = codecs;
  ctx.toString = function() {return "media context:id=" + this.media.id;};
  console.log("media_init:ctx=" + ctx);
  medias.set(media.id, ctx);
  ctx.mediaSource = new MediaSource();
  media.src = URL.createObjectURL(ctx.mediaSource);
  var table = document.getElementById(media.id + "s2");
  table.style.display = 'none';  //hide initial controls
  media.setAttribute('controls', '');  //show native controls
  ctx.mediaSource.addEventListener('sourceopen', function(event) {
    URL.revokeObjectURL(media.src);
    console.log("media_init.sourceopen:ctx=" + ctx);
    if (!MediaSource.isTypeSupported(ctx.codecs)) {
      console.log("Error:codecs not supported:" + ctx.codecs);
      return;
    }
    ctx.streamBuffer = ctx.mediaSource.addSourceBuffer(ctx.codecs);
  });
  media.play();
}

function media_uninit(media) {
  var ctx = medias.get(media.id);
  //TODO : stop/free resources
  medias.delete(media.id);
}

function media_add_buffer(media) {
  var ctx = medias.get(media.id);
  console.log("media_add_buffer:ctx=" + ctx + ":byteLength=" + bindata.byteLength + ":readyState=" + ctx.media.readyState + ":currentTime=" + ctx.media.currentTime);
  if (ctx.mediaSource.readyState !== 'open') {
    console.log("media_add_buffer:Error:MediaSource not open");
    return;
  }
  if (ctx.streamBuffer === null) {
    console.log("media_add_buffer:Error:StreamBuffer is null");
    return;
  }
  if (ctx.streamBuffer.updating) {
    console.log("media_add_buffer:Error:StreamBuffer is still updating");
    return;
  }
  ctx.streamBuffer.appendBuffer(bindata);
  if (ctx.media.readyState !== 4) {
    var bufs = media.buffered;
    var len = bufs.length;
    for(var i=0;i<len;i++) {
      console.log("buffered:" + bufs.start(i) + " to " + bufs.end(i));
    }
    if (len > 0) {
      ctx.media.currentTime = bufs.start(0);
      ctx.media.play();
    }
  }
}

function media_play(media) {
  console.log("media_play");
  media.play();
}

function media_pause(media) {
  console.log("media_pause");
  media.pause();
}

function media_stop(media) {
  console.log("media_stop");
  media.stop();
}

function media_seek(media, time) {
  console.log("media_seek:" + time);
  media.currentTime = time;
}

function media_onplay(media) {
  var msg = {
    event: "onplay",
    id: media.id
  };
  ws.send(JSON.stringify(msg));
}

function media_onpause(media) {
  var msg = {
    event: "onpause",
    id: media.id
  };
  ws.send(JSON.stringify(msg));
}

function media_onstop(media) {
  var msg = {
    event: "onstop",
    id: media.id
  };
  ws.send(JSON.stringify(msg));
}
