var gulp = require('gulp');
var concat = require('gulp-concat');
var filter = require('gulp-filter');
var mainBowerFiles = require('main-bower-files');

gulp.task('build-third-party-resources', function () {
    var mainFiles = mainBowerFiles();

    if (!mainFiles.length) {
        // No main files found. Skipping....
        return;
    }

    var jsFilter = filter('**/*.js');
    var cssFilter = filter('**/*.css');
    var otherFilesFilter = filter('**/*.{ttf,woff,eot,svg}');

    gulp.src(mainFiles)
        .pipe(jsFilter)
        .pipe(concat('third-party.js'))
        .pipe(gulp.dest('./build'))
        .pipe(jsFilter.restore())
        .pipe(cssFilter)
        .pipe(concat('third-party.css'))
        .pipe(cssFilter.restore())
        .pipe(otherFilesFilter)
        .pipe(otherFilesFilter.restore())
        .pipe(gulp.dest('./build'));
});

gulp.task('build-app-js', function(){
    gulp.src('./src/js/*.js')
        .pipe(concat('app.js'))
        .pipe(gulp.dest('./build'));
});

gulp.task('build-app-css', function(){
    gulp.src('./src/css/*.css')
        .pipe(concat('main.css'))
        .pipe(gulp.dest('./build'));
});

gulp.task('build-templates', function() {
    gulp.src('./src/*.html')
        .pipe(gulp.dest('./build'));
});

gulp.task('build-app-resources', ['build-app-js','build-app-css','build-templates'], function () {
});

gulp.task('build', ['build-app-resources', 'build-third-party-resources'], function () {
});