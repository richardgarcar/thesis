var gulp = require('gulp');
var concat = require('gulp-concat');
var filter = require('gulp-filter');
var mainBowerFiles = require('main-bower-files');
var del = require('del');

var filterByExtension = function (extension) {
    return filter(function (file) {
        return file.path.match(new RegExp('.' + extension + '$'));
    });
};

gulp.task('build-third-party-resources', function () {
    var mainFiles = mainBowerFiles();

    if (!mainFiles.length) {
        // No main files found. Skipping....
        return;
    }

    var jsFilter = filterByExtension('js');

    return gulp.src(mainFiles)
        .pipe(jsFilter)
        .pipe(concat('third-party.js'))
        .pipe(gulp.dest('./build'));
});

gulp.task('build-app-js', function(){
    gulp.src('./src/js/*.js')
        .pipe(concat('app.js'))
        .pipe(gulp.dest('./build'));
});

gulp.task('build-index', function() {
    gulp.src('./src/index.html')
        .pipe(gulp.dest('./build'));
});

gulp.task('build-app-resources', ['build-app-js', 'build-index'], function () {
});

gulp.task('build', ['build-app-resources', 'build-third-party-resources'], function () {
});