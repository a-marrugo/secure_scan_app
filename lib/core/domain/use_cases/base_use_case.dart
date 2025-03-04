/// Contract to handle use cases in the app
///
/// `Entity` = Entity to get on data state
///
/// `Params` = Parameters to handle on UseCase (`UseCase`)
abstract class BaseUseCase<Entity, Params> {
  /// Execute the use case
  Future<Entity> execute(Params params);
}

abstract class BaseStreamUseCase<Entity, Params> {
  Stream<Entity> execute(Params params);
}
